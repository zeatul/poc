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
import com.hawk.ecom.product.exception.AttrNameStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.AttrValueIsUsedRuntimeException;
import com.hawk.ecom.product.exception.AttrValueNotFoundRuntimeException;
import com.hawk.ecom.product.exception.DuplicateAttrValueRuntimeException;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.mapper.AttrNameMapper;
import com.hawk.ecom.product.persist.mapper.AttrValueMapper;
import com.hawk.ecom.product.persist.mapper.ProductAttrMapper;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.product.request.ListAttrValueParam;
import com.hawk.ecom.product.request.LoadAttrValueParam;
import com.hawk.ecom.product.request.RemoveAttrValueParam;
import com.hawk.ecom.product.request.UpdateAttrValueParam;
import com.hawk.ecom.product.request.UpdateAttrValueStatusParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DomainTools;

@Service
public class AttrValueService {

	@Autowired
	private MallAuthService authService;

	@Autowired
	private AttrValueMapper attrValueMapper;
	
	@Autowired
	private AttrNameMapper attrNameMapper;

	@Autowired
	private AttrNameService attrNameService;
	
	@Autowired
	private ProductAttrMapper productAttrMapper;

	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 属性值是否被引用
	 * @param id
	 */
	public boolean isAttrValueUsed(Integer id){
		if (id == null){
			throw new RuntimeException("isAttrValueUsed is is null ");
		}
		
		MybatisParam params = new MybatisParam().put("attrValueId", id);
		/**
		 * 是否被产品属性表引用
		 */
		if (productAttrMapper.count(params) > 0)
			return true;
		
		/**
		 * 是否被属性名表引用
		 */
		params.clear();
		params.put("pvid", id);
		if (attrNameMapper.count(params)>0)
			return true;
		return false;
	}
	

	public boolean exists(Integer id) {
		if (id == null)
			return false;
		MybatisParam params = new MybatisParam().put("id", id);
		return attrValueMapper.count(params) > 0;
	}

	public AttrValueDomain loadAttrValue(Integer id) {
		AttrValueDomain attrValueDomain = null;
		if (id != null) {
			attrValueDomain = attrValueMapper.load(id);
		} else {
			logger.error("loadAttrValue:id is null");
		}

		if (attrValueDomain == null) {
			throw new AttrValueNotFoundRuntimeException();
		}

		return attrValueDomain;
	}

	@Valid
	public AttrValueDomain createAttrValue(@NotNull("参数") @Valid CreateAttrValueParam createAttrValueParam) {
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		/**
		 * 校验
		 */
		AttrNameDomain attrNameDomain = attrNameService.loadAttrNameById(createAttrValueParam.getAttrNameId());
		if (attrNameDomain.getAttrNameStatus() != ConstAttr.AttrNameStatus.AVAIlABLE) {
			throw new AttrNameStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * create
		 */
		AttrValueDomain attrValueDomain = new AttrValueDomain();
		attrValueDomain.setAttrDisplayValue(createAttrValueParam.getAttrDisplayValue());
		attrValueDomain.setAttrNameId(attrNameDomain.getId());
		attrValueDomain.setAttrValue(createAttrValueParam.getAttrValue());
		attrValueDomain.setAttrDisplayEnValue(createAttrValueParam.getAttrDisplayEnValue());
		
//		if (StringTools.isNullOrEmpty(attrValueDomain.getAttrDisplayValue())) {
//			attrValueDomain.setAttrDisplayValue(attrValueDomain.getAttrValue());
//		}
		
		attrValueDomain.setAttrValueStatus(ConstAttr.AttrValueStatus.AVAIlABLE);
		attrValueDomain.setCategoryId(attrNameDomain.getCategoryId());

		Date now = new Date();
		attrValueDomain.setCreateDate(now);
		attrValueDomain.setCreateUserCode(AuthThreadLocal.getUserCode());

		attrValueDomain.setUpdateDate(now);
		attrValueDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());

		attrValueDomain.setId(pkGenService.genPk());

		try {
			attrValueMapper.insert(attrValueDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateAttrValueRuntimeException();
		}

		return attrValueDomain;
	}

	@Valid
	public List<AttrValueDomain> listAttrValue(@NotNull("参数") @Valid ListAttrValueParam listAttrValueParam) {
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		MybatisParam params = new MybatisParam();
		params.put("attrNameId", listAttrValueParam.getAttrNameId());
		params.put("categoryId", listAttrValueParam.getCategoryId());
		params.put("attrValueStatus", listAttrValueParam.getAttrValueStatus());

		params = MybatisTools.page(params, listAttrValueParam);

		return attrValueMapper.loadDynamicPaging(params);
	}

	@Valid
	public AttrValueDomain loadAttrValue(@NotNull("参数") @Valid LoadAttrValueParam loadAttrValueParam) {
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		return loadAttrValue(loadAttrValueParam.getId());
	}

	@Valid
	public void updateAttrValue(@NotNull("参数") @Valid UpdateAttrValueParam updateAttrValueParam) {
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		/**
		 * 校验存在性
		 */
		if (!exists(updateAttrValueParam.getId())) {
			throw new AttrValueNotFoundRuntimeException();
		}

		AttrValueDomain updateDomain = new AttrValueDomain();
		DomainTools.copy(updateAttrValueParam, updateDomain);
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());

		try {
			attrValueMapper.updateWithoutNull(updateDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateAttrValueRuntimeException();
		}
	}
	
	@Valid
	@Transactional
	public void updateAttrValueStatus(@NotNull("参数") @Valid UpdateAttrValueStatusParam updateAttrValueStatusParam) {
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		int status = updateAttrValueStatusParam.getAttrValueStatus();
		for (Integer id : updateAttrValueStatusParam.getIds()){
			AttrValueDomain attrValueDomain = loadAttrValue(id);
			if (status != attrValueDomain.getAttrValueStatus()){
				AttrValueDomain updateDomain = new AttrValueDomain();
				updateDomain.setAttrValueStatus(status);
				updateDomain.setId(id);
				updateDomain.setUpdateDate(new Date());
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
				attrValueMapper.updateWithoutNull(updateDomain);
			}
		}
	}
	

	@Valid
	@Transactional
	public void removeAttrValue(@NotNull("参数") @Valid RemoveAttrValueParam removeAttrValueParam) {
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		for (Integer id : removeAttrValueParam.getIds()){
			if (id == null){
				continue;
			}
			
			if (!exists(id)){
				throw new AttrValueNotFoundRuntimeException();
			}
			
			/**
			 * 校验参数值有没有没引用过
			 */
			if (isAttrValueUsed(id)){
				throw new AttrValueIsUsedRuntimeException();
			}
			
			/**
			 * TODO：保留历史
			 */
			attrValueMapper.delete(id);
		}
	}
}
