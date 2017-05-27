package com.hawk.ecom.mall.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.exception.SystemResourceNotFoundRuntimeException;
import com.hawk.ecom.mall.persist.domain.SystemResourceDomain;
import com.hawk.ecom.mall.persist.mapper.SystemResourceMapper;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class SystemResourceService {
	
	
	
	private final static SystemResourceDomain ROOT = new SystemResourceDomain();
	static
	{
		ROOT.setNodeCode("root");
		ROOT.setId(0l);
	}
	
	@Autowired
	private SystemResourceMapper systemResourceMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public SystemResourceDomain querySystemResourceByNodeCode(String nodeCode){
		if (StringTools.isNullOrEmpty(nodeCode)){
			logger.error("nodeCode is empty!!!");
			return null;
		}
		MybatisParam params = new MybatisParam().put("nodeCode", nodeCode);
		return MybatisTools.single(systemResourceMapper.loadDynamic(params));
	}
	
	@Valid
	public SystemResourceDomain createResource(@NotEmpty("参数") @Valid SystemCreateResourceParam systemCreateResourceParam){
		String parentNodeCode = systemCreateResourceParam.getParentNodeCode();
		SystemResourceDomain parent = ROOT;
		if (StringTools.isNotNullOrEmpty(parentNodeCode)  && !parentNodeCode.equalsIgnoreCase(parentNodeCode) ){
			parent = querySystemResourceByNodeCode(parentNodeCode);
			if (parent == null){
				throw new SystemResourceNotFoundRuntimeException();
			}
		}
		
		String nodeCode = systemCreateResourceParam.getNodeCode();
		if ("root".equalsIgnoreCase(nodeCode)){
			throw new RuntimeException("nodeCode shouldn't be null");
		}
		
		SystemResourceDomain systemResourceDomain = new SystemResourceDomain();
		systemResourceDomain.setCodePath(codePath);
		systemResourceDomain.setCreateDate(createDate);
		systemResourceDomain.setCreateUserCode(createUserCode);
		systemResourceDomain.setDepth(depth);
		systemResourceDomain.setNamePath(namePath);
		systemResourceDomain.setNodeCheckedIco(nodeCheckedIco);
		systemResourceDomain.setNodeCode(nodeCode);
		systemResourceDomain.setNodeDesc(nodeDesc);
		systemResourceDomain.setNodeGreyIco(nodeGreyIco);
		systemResourceDomain.setNodeIco(nodeIco);
		systemResourceDomain.setNodeName(nodeName);
		systemResourceDomain.setNodeOrder(nodeOrder);
		systemResourceDomain.setNodeRiseIco(nodeRiseIco);
		systemResourceDomain.setNodeStatus(nodeStatus);
		systemResourceDomain.setNodeSubType(nodeSubType);
		systemResourceDomain.setNodeType(nodeType);
		systemResourceDomain.setNodeValue(nodeValue);
		systemResourceDomain.setNodeValueType(nodeValueType);
		systemResourceDomain.setPid(pid);
		systemResourceDomain.setUpdateDate(updateDate);
		systemResourceDomain.setUpdateUserCode(updateUserCode);
		
		systemResourceDomain.setId(id);
		systemResourceDomain.setIdPath(idPath);
		
	}

}
