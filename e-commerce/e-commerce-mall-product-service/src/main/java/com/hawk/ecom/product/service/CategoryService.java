package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

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
import com.hawk.ecom.product.exception.CategoryHasDifferentParentRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsUsedRuntimeException;
import com.hawk.ecom.product.exception.CategoryNotFoundRuntimeException;
import com.hawk.ecom.product.exception.DuplicateCategoryRuntimeException;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.mapper.AttrNameMapper;
import com.hawk.ecom.product.persist.mapper.CategoryBrandMapMapper;
import com.hawk.ecom.product.persist.mapper.CategoryMapper;
import com.hawk.ecom.product.persist.mapper.CategorySupplierMapMapper;
import com.hawk.ecom.product.persist.mapper.ProductMapper;
import com.hawk.ecom.product.persist.mapperex.CategoryExMapper;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.ExchangeCategoryOrderParam;
import com.hawk.ecom.product.request.ListCategoryParam;
import com.hawk.ecom.product.request.LoadCategoryParam;
import com.hawk.ecom.product.request.RemoveCategoryParam;
import com.hawk.ecom.product.request.UpdateCategoryParam;
import com.hawk.ecom.product.request.UpdateCategoryStatusParam;
import com.hawk.ecom.product.request.UpdateCategoryVariantStatusParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DomainTools;
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
	private AttrNameMapper attrNameMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CategoryBrandMapMapper categoryBrandMapMapper;
	
	@Autowired
	private CategorySupplierMapMapper categorySupplierMapMapper;
	
	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private final static CategoryDomain ROOT = new CategoryDomain();
	static {
		ROOT.setCategoryCode("root");
		ROOT.setId(0);
		ROOT.setDepth(0);
		ROOT.setIdPath("/0");
		ROOT.setCategoryName("root");
		ROOT.setIsLeaf(0);

	}
	
	public boolean isCategoryUsed(Integer id){
		if (id == null){
			throw new RuntimeException("isCategoryUsed : id is null");
		}
		
		/**
		 * TODO：查询有没有该分录下的属性集合
		 */
		MybatisParam params = new MybatisParam().put("categoryId",id);
		if (attrNameMapper.count(params)>0)
			return true;
		
		/**
		 * TODO:查询有没有产品记录
		 */
		if (productMapper.count(params)>0)
			return true;
		
		/**
		 * TODO:查询有没有对应的品牌记录
		 */
		if (categoryBrandMapMapper.count(params)>0)
			return true;
		
		/**
		 * TODO:查询有没有对应的供应商记录
		 */
		if (categorySupplierMapMapper.count(params)>0)
			return true;
		
		return false;
		
	}
	
	/**
	 * 根据主键查询记录，找不到就抛异常
	 * @param id
	 * @return
	 */
	public CategoryDomain loadCategory(Integer id){
		CategoryDomain categoryDomain = null; 
		if (id != null){
			categoryDomain = categoryMapper.load(id);
		}else{
			logger.error("loadCategory param id is null");
		}
		if (categoryDomain == null){
			throw new CategoryNotFoundRuntimeException();
		}
		return categoryDomain;
	}
	
	/**
	 * 产品目录主键
	 * @param id
	 * @return
	 */
	public boolean exists(Integer  id){
		if (id == null){
			logger.error("queryCategoryDomainById param id is null");
			return false;
		}
		MybatisParam params = new MybatisParam().put("id", id);
		return categoryMapper.count(params) > 0;
	}
	
	public CategoryDomain queryCategoryDomainById(Integer  id){
		if (id == null){
			logger.error("queryCategoryDomainById param id is null");
			return null;
		}
		return categoryMapper.load(id);
	}
	
	private CategoryDomain queryParentCategoryById(Integer  pid){
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
		
		categoryDomain.setCategoryStatus(ConstCategory.CategoryStatus.AVAILABLE);
		categoryDomain.setCategoryVariantStatus(ConstCategory.CategoryVariantStatus.EDITING);
		
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
		
		Integer  id = pkGenService.genPk();
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
	
	/**
	 * 
	 * @param updateCategoryParam
	 */
	@Valid
	public void updateCategory(@NotNull("参数") @Valid UpdateCategoryParam updateCategoryParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
//		CategoryDomain categoryDomain = loadCategory(updateCategoryParam.getId());
		
		if (!exists(updateCategoryParam.getId())){
			throw new CategoryNotFoundRuntimeException();
		}
		
		
		CategoryDomain updateDomain = new CategoryDomain();
		DomainTools.copy(updateCategoryParam, updateDomain);
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		updateDomain.setUpdateDate(new Date());
		categoryMapper.updateWithoutNull(updateDomain);
	}
	
	@Valid
	@Transactional
	public void updateCategoryStatus(@NotNull("参数") @Valid UpdateCategoryStatusParam updateCategoryStatusParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		int status = updateCategoryStatusParam.getCategoryStatus();
		for(Integer  id  : updateCategoryStatusParam.getIds()){
			CategoryDomain categoryDomain = loadCategory(id);
			if (categoryDomain.getCategoryStatus() != status){
				CategoryDomain updateDomain = new CategoryDomain();
				updateDomain.setId(id);
				updateDomain.setCategoryStatus(status);
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
				updateDomain.setUpdateDate(new Date());
				categoryMapper.updateWithoutNull(updateDomain);
			}
		}
	}
	
	@Valid
	@Transactional
	public void updateCategoryVariantStatus(@NotNull("参数") @Valid UpdateCategoryVariantStatusParam updateCategoryVariantStatusParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		int status = updateCategoryVariantStatusParam.getCategoryVariantStatus();
		for(Integer  id  : updateCategoryVariantStatusParam.getIds()){
			CategoryDomain categoryDomain = loadCategory(id);
			if (categoryDomain.getCategoryVariantStatus() != status){
				/**
				 * TODO:检测是否符合模板发布条件
				 */
				CategoryDomain updateDomain = new CategoryDomain();
				updateDomain.setId(id);
				updateDomain.setCategoryVariantStatus(status);
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
				updateDomain.setUpdateDate(new Date());
				categoryMapper.updateWithoutNull(updateDomain);
			}
		}
	}
	
	@Valid
	public List<CategoryDomain> listCategory(@NotNull("参数") @Valid ListCategoryParam listCategoryParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(listCategoryParam.getOrder())){
			listCategoryParam.setOrder("pid asc , object_order asc");
		}
		
		MybatisParam params = new MybatisParam() ;
		params.put("pid", listCategoryParam.getPid());
		params.put("categoryStatus", listCategoryParam.getCategoryStatus());
		params.put("categoryVariantStatus", listCategoryParam.getCategoryVariantStatus());
		params.put("isLeaf", listCategoryParam.getIsLeaf());
		
		params = MybatisTools.page(params, listCategoryParam);
		return categoryMapper.loadDynamicPaging(params);
	}
	
	@Valid
	public CategoryDomain loadCategory(@NotEmpty("参数") @Valid LoadCategoryParam loadCategoryParam) {
		
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		return loadCategory(loadCategoryParam.getId());
	}
	
	
	
	private int countSub(long pid){
		MybatisParam params = new MybatisParam().put("pid", pid);
		return categoryMapper.count(params);
	}
	
	@Valid
	@Transactional
	public void exchangeCategoryOrder(@NotNull("参数") @Valid ExchangeCategoryOrderParam exchangeCategoryOrderParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		CategoryDomain a = loadCategory(exchangeCategoryOrderParam.getCategoryIdA());
		CategoryDomain b = loadCategory(exchangeCategoryOrderParam.getCategoryIdB());
		
		if (!a.getPid().equals(b.getPid())){
			throw new CategoryHasDifferentParentRuntimeException();
		}
		
		CategoryDomain updateDomain = new CategoryDomain();
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		
		updateDomain.setId(a.getId());
		updateDomain.setObjectOrder(b.getObjectOrder());
		
		categoryMapper.updateWithoutNull(updateDomain);
		
		updateDomain.setId(b.getId());
		updateDomain.setObjectOrder(a.getObjectOrder());
		
		categoryMapper.updateWithoutNull(updateDomain);
	}
	
	@Valid
	@Transactional
	public void removeCategory(@NotNull("参数") @Valid RemoveCategoryParam removeCategoryParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		
		
		for(Integer id :removeCategoryParam.getIds()){
						
			if (!exists(id)){
				throw new CategoryNotFoundRuntimeException();
			}
			
			/**
			 * 查询有没有子节点
			 */
			if (countSub(id)>0){
				throw new CategoryHasChildRuntimeException();
			}
			
			
			if (isCategoryUsed(id)){
				throw new CategoryIsUsedRuntimeException();
			}
			
			
			
			categoryMapper.delete(id);
			
		}
		
	}

}
