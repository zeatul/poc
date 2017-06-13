package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.exception.CategoryHasChildRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryNotFoundRuntimeException;
import com.hawk.ecom.product.exception.DuplicateCategoryRuntimeException;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.mapper.CategoryMapper;
import com.hawk.ecom.product.persist.mapperex.CategoryExMapper;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.ListSubCategoryParam;
import com.hawk.ecom.product.request.LoadCategoryParam;
import com.hawk.ecom.product.request.RemoveCategoryParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class CategoryService {
	
	@Autowired
	private MallAuthService authService;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private final static CategoryDomain ROOT = new CategoryDomain();
	static {
		ROOT.setCategoryCode("root");
		ROOT.setId(0l);
		ROOT.setDepth(0);
		ROOT.setIdPath("/0");
		ROOT.setCategoryName("root");
		ROOT.setIsLeaf(0);

	}
	
	public CategoryDomain queryCategoryDomainById(Long id){
		if (id == null){
			logger.error("queryCategoryDomainById param id is null");
			return null;
		}
		return categoryMapper.load(id);
	}
	
	private CategoryDomain queryParentCategoryById(Long pid){
		if (pid == null || pid == 0)
			return ROOT;
		CategoryDomain parent =  queryCategoryDomainById(pid);
		if (parent == null){
			throw new CategoryNotFoundRuntimeException();
		}
		return parent;
	}
	
	@Valid
	public CategoryDomain createCategory(@NotNull("参数") @Valid CreateCategoryParam createCategoryParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		CategoryDomain parent = queryParentCategoryById(createCategoryParam.getPid());
		
		if (ConstBoolean.parse(parent.getIsLeaf())){
			throw new CategoryIsLeafRuntimeException();
		}
		
		
		CategoryDomain categoryDomain = new CategoryDomain();
		Date now = new Date();
		categoryDomain.setCategoryDesc(createCategoryParam.getCategoryDesc());
		categoryDomain.setCategoryHomePage(createCategoryParam.getCategoryHomePage());
		categoryDomain.setCategoryLogo(createCategoryParam.getCategoryLogo());
		categoryDomain.setCategoryName(createCategoryParam.getCategoryName());
		
		categoryDomain.setCreateDate(now);
		categoryDomain.setCreateUserCode(createCategoryParam.getOperatorCode());
		categoryDomain.setDepth(parent.getDepth()+1);
		categoryDomain.setIsLeaf(createCategoryParam.getIsLeaf());
		categoryDomain.setUpdateDate(now);
		categoryDomain.setUpdateUserCode(createCategoryParam.getOperatorCode());
				
		categoryDomain.setPid(parent.getId());		
		categoryDomain.setCategoryStatus(ConstCategory.CategoryStatus.NORMAL);
		
		Integer objectOrder = createCategoryParam.getObjectOrder();
		if (objectOrder == null) {
			objectOrder = categoryExMapper.maxObjectOrder(parent.getId());
			if (objectOrder == null) {
				objectOrder = 100;
			} else {
				objectOrder = objectOrder + 100;
			}
		}
		categoryDomain.setObjectOrder(objectOrder);
		
		Long id = pkGenService.genPk();
		String categoryCode = createCategoryParam.getCategoryCode();
		if (StringTools.isNullOrEmpty(categoryCode)){
			categoryCode = id.toString();
		}
		
		categoryDomain.setCategoryCode(categoryCode);
		categoryDomain.setId(id);
		categoryDomain.setIdPath(parent.getIdPath() + "/" + id);
		
		
		try {
			categoryMapper.insert(categoryDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateCategoryRuntimeException();
		}

		return categoryDomain;
		
	}
	
	@Valid
	public List<CategoryDomain> listSubCategory(@NotNull("参数") @Valid ListSubCategoryParam listSubCategoryParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(listSubCategoryParam.getOrder())){
			listSubCategoryParam.setOrder("object_order asc");
		}
		
		MybatisParam params = MybatisTools.page(new MybatisParam().put("pid", listSubCategoryParam.getPid()), listSubCategoryParam);
		return categoryMapper.loadDynamicPaging(params);
	}
	
	@Valid
	public CategoryDomain loadCategory(@NotEmpty("参数") @Valid LoadCategoryParam loadCategoryParam) {
		
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		return loadCategory(loadCategoryParam.getId());
	}
	
	public CategoryDomain loadCategory(long id){
		
		CategoryDomain categoryDomain =  categoryMapper.load(id);
		
		if (categoryDomain == null) {
			throw new CategoryNotFoundRuntimeException();
		}
		
		return categoryDomain;
	}
	
	private int countSub(long pid){
		MybatisParam params = new MybatisParam().put("pid", pid);
		return categoryMapper.count(params);
	}
	
	@Valid
	@Transactional
	public void removeCategory(@NotNull("参数") @Valid RemoveCategoryParam removeCategoryParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		
		
		for(Long id :removeCategoryParam.getIds()){
						
			
			/**
			 * 查询有没有子节点
			 */
			if (countSub(id)>0){
				throw new CategoryHasChildRuntimeException();
			}
			
			/**
			 * TODO：查询有没有模板
			 */
			
			categoryMapper.delete(id);
			
		}
		
	}

}
