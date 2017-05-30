package com.hawk.ecom.mall.service;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.constant.ConstSystemResource;
import com.hawk.ecom.mall.exception.DuplicateMallUserRuntimeException;
import com.hawk.ecom.mall.exception.DuplicateSystemResourceRuntimeException;
import com.hawk.ecom.mall.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.mall.exception.SystemResourceNotFoundRuntimeException;
import com.hawk.ecom.mall.persist.domain.SystemResourceDomain;
import com.hawk.ecom.mall.persist.mapper.SystemResourceMapper;
import com.hawk.ecom.mall.persist.mapperex.SystemResourceExMapper;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class SystemResourceService {

	@Autowired
	@Qualifier("systemShorkPkSequenceService")
	private PkGenService systemShorkPkSequenceService;

	@Autowired
	private SystemResourceExMapper systemResourceExMapper;

	@Autowired
	private SystemResourceMapper systemResourceMapper;
	
	@Autowired
	private AuthService authService;

	private final static SystemResourceDomain ROOT = new SystemResourceDomain();
	static {
		ROOT.setNodeCode("root");
		ROOT.setId(0l);
		ROOT.setDepth(0);
		ROOT.setIdPath("/0");
		ROOT.setNodeName("root");
		ROOT.setNamePath("/root");
		ROOT.setCodePath("/root");

	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public SystemResourceDomain querySystemResourceByNodeCode(String nodeCode) {
		if (StringTools.isNullOrEmpty(nodeCode)) {
			logger.error("nodeCode is empty!!!");
			return null;
		}
		MybatisParam params = new MybatisParam().put("nodeCode", nodeCode);
		return MybatisTools.single(systemResourceMapper.loadDynamic(params));
	}

	@Valid
	public SystemResourceDomain createResource(@NotEmpty("参数") @Valid SystemCreateResourceParam systemCreateResourceParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("superadmin","admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		
		String parentNodeCode = systemCreateResourceParam.getParentNodeCode();
		SystemResourceDomain parent = ROOT;
		if (StringTools.isNotNullOrEmpty(parentNodeCode) && !parentNodeCode.equalsIgnoreCase(ROOT.getNodeCode())) {
			parent = querySystemResourceByNodeCode(parentNodeCode);
			if (parent == null) {
				throw new SystemResourceNotFoundRuntimeException();
			}
		}

		String nodeCode = systemCreateResourceParam.getNodeCode();
		if ("root".equalsIgnoreCase(nodeCode)) {
			throw new RuntimeException("nodeCode shouldn't be root");
		}

		Date curDate = new Date();
		SystemResourceDomain systemResourceDomain = new SystemResourceDomain();

		systemResourceDomain.setCreateDate(curDate);
		systemResourceDomain.setCreateUserCode(systemCreateResourceParam.getOperatorCode());
		systemResourceDomain.setDepth(parent.getDepth() + 1);

		systemResourceDomain.setNodeCheckedIco(null);

		systemResourceDomain.setNodeDesc(systemCreateResourceParam.getNodeDesc());

		systemResourceDomain.setNodeGreyIco(null);
		systemResourceDomain.setNodeIco(systemCreateResourceParam.getNodeIco());
		String nodeName = systemCreateResourceParam.getNodeName().trim();
		if (nodeName.indexOf("/") > -1) {
			throw new RuntimeException("nodeName shouln't include / char");
		}
		systemResourceDomain.setNodeName(systemCreateResourceParam.getNodeName());

		systemResourceDomain.setNodeRiseIco(null);
		systemResourceDomain.setNodeStatus(ConstSystemResource.NodeStatus.NORMAL);
		systemResourceDomain.setNodeSubType(ConstSystemResource.NodeSubType.OTHER);
		systemResourceDomain.setNodeType(ConstSystemResource.NodeType.MENU);
		systemResourceDomain.setNodeValue(systemCreateResourceParam.getNodeValue());
		systemResourceDomain.setNodeValueType(ConstSystemResource.NodeValueType.HTTP);
		systemResourceDomain.setPid(parent.getId());
		systemResourceDomain.setUpdateDate(curDate);
		systemResourceDomain.setUpdateUserCode(systemCreateResourceParam.getOperatorCode());

		Long id = systemShorkPkSequenceService.genPk();
		systemResourceDomain.setId(id);
		if (StringTools.isNullOrEmpty(nodeCode)) {
			nodeCode = systemResourceDomain.getId().toString();
		} else {
			nodeCode = nodeCode.trim();
		}
		if (nodeName.indexOf("/") > -1) {
			throw new RuntimeException("nodeCode shouln't include / char");
		}
		systemResourceDomain.setNodeCode(nodeCode);
		systemResourceDomain.setCodePath(parent.getCodePath() + "/" + nodeCode);
		systemResourceDomain.setNamePath(parent.getNamePath() + "/" + nodeName);
		systemResourceDomain.setIdPath(parent.getIdPath() + "/" + id);
		Integer objectOrder = systemCreateResourceParam.getObjectOrder();
		if (objectOrder == null) {
			objectOrder = systemResourceExMapper.maxObjectOrder(parent.getId());

			if (objectOrder == null) {
				objectOrder = 100;
			} else {
				objectOrder = objectOrder + 100;
			}

		}
		systemResourceDomain.setObjectOrder(objectOrder);

		try {
			systemResourceMapper.insert(systemResourceDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateSystemResourceRuntimeException();
		}

		return systemResourceDomain;
	}

}
