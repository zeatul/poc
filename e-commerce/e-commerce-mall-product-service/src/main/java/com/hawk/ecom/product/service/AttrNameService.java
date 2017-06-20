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
import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.exception.AttrNameIsUsedRuntimeException;
import com.hawk.ecom.product.exception.AttrNameNotFoundRuntimeException;
import com.hawk.ecom.product.exception.AttrNamePidIsDifferentWithAttrNameIdOfAttrValueRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsDifferentRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsNotLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryTemplateStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.DuplicateAttrNameRuntimeException;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.mapper.AttrNameMapper;
import com.hawk.ecom.product.persist.mapper.AttrValueMapper;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.ListAttrNameOfCategoryParam;
import com.hawk.ecom.product.request.ListAttrNameParam;
import com.hawk.ecom.product.request.LoadAttrNameParam;
import com.hawk.ecom.product.request.RemoveAttrNameParam;
import com.hawk.ecom.product.request.UpdateAttrNameParam;
import com.hawk.ecom.product.request.UpdateAttrNameStatusParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class AttrNameService {
	
	@Autowired
	private MallAuthService authService;	
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AttrNameMapper  attrNameMapper;
	
	@Autowired
	private AttrValueService attrValueService;
	
	@Autowired
	private AttrValueMapper attrValueMapper;
	
	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;
	
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public boolean isAttrNameUsed(Integer attrNameId) {
		if (attrNameId == null) {
			throw new RuntimeException("attrNameId is null");
		}
		MybatisParam params = new MybatisParam().put("attrNameId", attrNameId);

		return attrValueMapper.count(params) > 0;
	}
	
	public boolean exists(Integer  id){
		if (id == null){
			logger.error("exists : id is null");
			return false;
		}
		
		MybatisParam params = new MybatisParam().put("id", id);
		
		return attrNameMapper.count(params)>0;
	}
	
	public AttrNameDomain loadById(Integer id){
		AttrNameDomain attrNameDomain = null;
		if (id != null){
			attrNameDomain = attrNameMapper.load(id);
		}
		
		if (attrNameDomain == null){
			throw new AttrNameNotFoundRuntimeException();
		}
		
		return attrNameDomain;
	}
	
	/**
	 * 只有产品目录类型为叶子节点，且模板状态为编辑状态时，才能操作
	 * @param createAttrParam
	 * @return
	 */
	@Valid
	public AttrNameDomain createAttrName(@NotNull("参数") @Valid CreateAttrNameParam createAttrNameParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		/**
		 * 校验
		 */
		CategoryDomain categoryDomain = categoryService.loadCategory(createAttrNameParam.getCategoryId());
		if (!ConstBoolean.parse(categoryDomain.getIsLeaf())){
			throw new CategoryIsNotLeafRuntimeException();
		}
		if (categoryDomain.getCategoryTemplateStatus() != ConstCategory.CategoryTemplateStatus.EDITING){
			throw new CategoryTemplateStatusIsNotAcceptableRuntimeException();
		}
		
		/**
		 * 创建
		 */
		AttrNameDomain attrNameDomain = new AttrNameDomain();
		attrNameDomain.setAttrName(createAttrNameParam.getAttrName());
		attrNameDomain.setAttrNameBusinessType(createAttrNameParam.getAttrNameBusinessType());
		
		
		attrNameDomain.setAttrNameStatus(ConstAttr.AttrNameStatus.AVAIlABLE);
		attrNameDomain.setAttrValueType(createAttrNameParam.getAttrValueType());
		attrNameDomain.setCategoryId(createAttrNameParam.getCategoryId());
		
		
		attrNameDomain.setIsSearch(createAttrNameParam.getIsSearch());
		
		/**
		 * 检验PID,必须存在，且和产品分类目录ID必须一致
		 */
		Integer  pid = createAttrNameParam.getPid();
		if (pid != 0){
			AttrNameDomain parent = loadById(pid);
			if (!parent.getCategoryId().equals(createAttrNameParam.getCategoryId())){
				throw new CategoryIsDifferentRuntimeException();
			}
		}		
		attrNameDomain.setPid(pid);
		
		
		/**
		 * 检验PVID,必须存在,且和产品分类目录ID必须一致
		 * 且Pvid对应的值的attrNameId 必须和pid一致
		 */
		Integer pvid  = createAttrNameParam.getPvid();
		if (pvid != 0){
			AttrValueDomain parentValue = attrValueService.loadAttrValue(pvid);
			if (!parentValue.getCategoryId().equals(createAttrNameParam.getCategoryId())){
				throw new CategoryIsDifferentRuntimeException();
			}
			
			if (!parentValue.getAttrNameId().equals(pid)){
				throw new AttrNamePidIsDifferentWithAttrNameIdOfAttrValueRuntimeException();
			}
		}
		attrNameDomain.setPvid(pvid);
		
		Date now = new Date();
		attrNameDomain.setCreateDate(now);
		attrNameDomain.setCreateUserCode(AuthThreadLocal.getUserCode());
		attrNameDomain.setUpdateDate(now);
		attrNameDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		
		attrNameDomain.setId(pkGenService.genPk());	
		
		try {
			attrNameMapper.insert(attrNameDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateAttrNameRuntimeException();
		}

		return attrNameDomain;
	}

	@Valid
	public void updateAttrName(@NotNull("参数") @Valid UpdateAttrNameParam updateAttrNameParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		/**
		 * 存在性
		 */
		if (!exists(updateAttrNameParam.getId())){
			throw new AttrNameNotFoundRuntimeException();
		}
		
		AttrNameDomain updateDomain = new AttrNameDomain();
		DomainTools.copy(updateAttrNameParam, updateDomain);
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		
		/**
		 * 入库
		 */		
		try {
			attrNameMapper.updateWithoutNull(updateDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateAttrNameRuntimeException();
		}
	}
	
	@Valid
	@Transactional
	public void updateAttrNameStatus(@NotNull("参数") @Valid UpdateAttrNameStatusParam updateAttrNameStatusParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		int status = updateAttrNameStatusParam.getAttrNameStatus();
		for (Integer  id : updateAttrNameStatusParam.getIds()){
			AttrNameDomain attrNameDomain = loadById(id);
			if (attrNameDomain.getAttrNameStatus() != status){
				AttrNameDomain updateDomain = new AttrNameDomain();
				updateDomain.setAttrNameStatus(updateAttrNameStatusParam.getAttrNameStatus());
				updateDomain.setUpdateDate(new Date());
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
				attrNameMapper.updateWithoutNull(updateDomain);
			}
		}
	}
	
	@Valid
	public List<AttrNameDomain> listAttrNameOfCategory(@NotNull("参数") @Valid ListAttrNameOfCategoryParam listAttrNameOfCategoryParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		MybatisParam params = new MybatisParam().put("categoryId", listAttrNameOfCategoryParam.getCategoryId());
		return attrNameMapper.loadDynamic(params);
	}
	
	@Valid
	public List<AttrNameDomain> listAttrName(@NotNull("参数") @Valid ListAttrNameParam listAttrNameParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(listAttrNameParam.getOrder())){
			listAttrNameParam.setOrder("create_date desc");
		}
		
		MybatisParam params = MybatisTools.page(new MybatisParam(), listAttrNameParam);
		return attrNameMapper.loadDynamicPaging(params);
	}
	
	@Valid
	public AttrNameDomain loadAttrName(@NotNull("参数") @Valid LoadAttrNameParam loadAttrNameParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		return loadById(loadAttrNameParam.getId());
	}
	
	@Valid
	@Transactional
	public void removeAttrName(@NotNull("参数") @Valid RemoveAttrNameParam removeAttrNameParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		for(Integer  id : removeAttrNameParam.getIds()){
			/**
			 * 存在性
			 */
			if (!exists(id)){
				throw new AttrNameNotFoundRuntimeException();
			}
			
			/**
			 * 校验，是否已经有attr_value 存在
			 */
			if (isAttrNameUsed(id)){
				throw new AttrNameIsUsedRuntimeException();
			}
			
			attrNameMapper.delete(id);
		}
	}
	
}
