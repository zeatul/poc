package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.exception.AttrNameStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.DuplicateAttrValueRuntimeException;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.mapper.AttrValueMapper;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class AttrValueService {
	
	@Autowired
	private MallAuthService authService;	
	
	@Autowired
	private AttrValueMapper attrValueMapper;
	
	@Autowired
	private AttrNameService attrNameService;
	
	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public boolean isAttrNameUsed(Integer  attrNameId){
		if (attrNameId == null){
			throw new RuntimeException("attrNameId is null");
		}
		MybatisParam params = new MybatisParam().put("attrNameId", attrNameId);
		
		return attrValueMapper.count(params)>0;
	}
	
	@Valid
	public AttrValueDomain createAttrValue(@NotNull("参数") @Valid CreateAttrValueParam createAttrValueParam){
		/**
		 * 权限
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		/**
		 * 校验
		 */
		AttrNameDomain attrNameDomain = attrNameService.loadById(createAttrValueParam.getAttrNameId());
		if (attrNameDomain.getAttrNameStatus() != ConstAttr.AttrNameStatus.AVAIlABLE){
			throw new AttrNameStatusIsNotAcceptableRuntimeException();
		}
		
		/**
		 * create
		 */
		AttrValueDomain attrValueDomain = new AttrValueDomain();
		attrValueDomain.setAttrDisplayValue(createAttrValueParam.getAttrDisplayValue());
		attrValueDomain.setAttrNameId(attrNameDomain.getId());
		attrValueDomain.setAttrValue(createAttrValueParam.getAttrValue());
		if (StringTools.isNullOrEmpty(attrValueDomain.getAttrDisplayValue())){
			attrValueDomain.setAttrDisplayValue(attrValueDomain.getAttrValue());
		}
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
}
