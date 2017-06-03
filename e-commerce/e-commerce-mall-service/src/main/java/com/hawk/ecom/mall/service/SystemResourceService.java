package com.hawk.ecom.mall.service;

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

import com.hawk.ecom.mall.constant.ConstSystemResource;
import com.hawk.ecom.mall.exception.DuplicateSystemResourceRuntimeException;
import com.hawk.ecom.mall.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.mall.exception.SystemResourceNotFoundRuntimeException;
import com.hawk.ecom.mall.persist.domain.SystemResourceDomain;
import com.hawk.ecom.mall.persist.mapper.SystemResourceMapper;
import com.hawk.ecom.mall.persist.mapperex.SystemResourceExMapper;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.ecom.mall.request.SystemExchangeResourceOrderParam;
import com.hawk.ecom.mall.request.SystemListResourceParam;
import com.hawk.ecom.mall.request.SystemLoadResourceParam;
import com.hawk.ecom.mall.request.SystemRemoveResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceStatusParam;
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
import com.hawk.ecom.mall.exception.SystemResourceHasChildRuntimeException;
import com.hawk.ecom.mall.exception.SystemResourceHasDifferentParentRuntimeException;

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
	
	private SystemResourceDomain queryParentSystemResourceByNodeCode(String parentNodeCode){
		SystemResourceDomain parent = ROOT;
		if (StringTools.isNotNullOrEmpty(parentNodeCode) && !parentNodeCode.equalsIgnoreCase(ROOT.getNodeCode())) {
			parent = querySystemResourceByNodeCode(parentNodeCode);
			if (parent == null) {
				throw new SystemResourceNotFoundRuntimeException();
			}
		}
		return parent;
	}
	
	private int countSub(long pid){
		MybatisParam params = new MybatisParam().put("pid", pid);
		return systemResourceMapper.count(params);
	}
	
	@Valid
	public SystemResourceDomain loadSystemResource(@NotEmpty("参数") @Valid SystemLoadResourceParam systemLoadResourceParam) {
		
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		SystemResourceDomain systemResourceDomain =  querySystemResourceByNodeCode(systemLoadResourceParam.getNodeCode());
		
		if (systemResourceDomain == null) {
			throw new SystemResourceNotFoundRuntimeException();
		}
		
		return systemResourceDomain;
	}

	@Valid
	public SystemResourceDomain createResource(@NotEmpty("参数") @Valid SystemCreateResourceParam systemCreateResourceParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		
		SystemResourceDomain parent =  queryParentSystemResourceByNodeCode(systemCreateResourceParam.getParentNodeCode());

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
		systemResourceDomain.setNodeStatus(ConstSystemResource.NodeStatus.EDITING);
		systemResourceDomain.setNodeSubType(ConstSystemResource.NodeSubType.OTHER);
		systemResourceDomain.setNodeType(ConstSystemResource.NodeType.MENU);
		systemResourceDomain.setNodeValue(systemCreateResourceParam.getNodeValue());
		systemResourceDomain.setNodeValueType(ConstSystemResource.NodeValueType.HTTP);
		systemResourceDomain.setPid(parent.getId());
		systemResourceDomain.setUpdateDate(curDate);
		systemResourceDomain.setReserved(ConstBoolean.FALSE);
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

	@Valid
	public List<SystemResourceDomain> listResource(@NotNull("参数") @Valid SystemListResourceParam systemListResourceParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(systemListResourceParam.getOrder())){
			systemListResourceParam.setOrder("object_order asc");
		}
		
		SystemResourceDomain parent =  queryParentSystemResourceByNodeCode(systemListResourceParam.getParentNodeCode());
		
		MybatisParam params = MybatisTools.page(new MybatisParam().put("pid", parent.getId()), systemListResourceParam);
		return systemResourceMapper.loadDynamicPaging(params);
	}
	
	@Valid
	public void updateResource(@NotNull("参数") @Valid SystemUpdateResourceParam systemUpdateResourceParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		String nodeCode = systemUpdateResourceParam.getNodeCode();
		if (ROOT.getNodeCode().equalsIgnoreCase(nodeCode)) {
			throw new RuntimeException("nodeCode shouldn't be root");
		}
		SystemResourceDomain node = querySystemResourceByNodeCode(nodeCode);
		if (node == null){
			throw new SystemResourceNotFoundRuntimeException();
		}
		SystemResourceDomain update = new SystemResourceDomain();
		DomainTools.copy(systemUpdateResourceParam, update);
		update.setId(node.getId());
		update.setUpdateDate(new Date());
		update.setUpdateUserCode(systemUpdateResourceParam.getOperatorCode());
		systemResourceMapper.updateWithoutNull(update);
	}
	
	@Valid
	@Transactional
	public void removeResource(@NotNull("参数") @Valid SystemRemoveResourceParam systemRemoveResourceParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		
		
		for(String nodeCode :systemRemoveResourceParam.getNodeCodes()){
			SystemResourceDomain node = querySystemResourceByNodeCode(nodeCode);
			if (node == null){
				throw new SystemResourceNotFoundRuntimeException();
			}
			
			if (ROOT.getNodeCode().equalsIgnoreCase(nodeCode)) {
				throw new RuntimeException("nodeCode shouldn't be root");
			}
			
			/**
			 * 查询是不是保留节点
			 */
			if (ConstBoolean.parse(node.getReserved())){
				throw new RuntimeException("系统保留节点,不能删除");
			}
			
			/**
			 * 查询有没有子节点
			 */
			if (countSub(node.getId())>0){
				throw new SystemResourceHasChildRuntimeException();
			}
			
			systemResourceMapper.delete(node.getId());
			
		}
		
	}
	
	@Valid
	@Transactional
	public void updateResourceStatus(@NotNull("参数") @Valid SystemUpdateResourceStatusParam systemUpdateResourceStatusParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		for(String nodeCode :systemUpdateResourceStatusParam.getNodeCodes()){
			SystemResourceDomain node = querySystemResourceByNodeCode(nodeCode);
			if (node == null){
				throw new SystemResourceNotFoundRuntimeException();
			}
			
			if (ROOT.getNodeCode().equalsIgnoreCase(nodeCode)) {
				throw new RuntimeException("nodeCode shouldn't be root");
			}

			int newNodeStatus = systemUpdateResourceStatusParam.getNodeStatus();
			if (!(ConstSystemResource.NodeStatus.ACTIVATED == newNodeStatus || ConstSystemResource.NodeStatus.EDITING == newNodeStatus
					|| ConstSystemResource.NodeStatus.FORBIDDEN == newNodeStatus)){
				throw new RuntimeException("nodeStatus value is illegal!!!");
			}
			
			
			SystemResourceDomain update = new SystemResourceDomain();
			update.setId(node.getId());
			update.setNodeStatus(newNodeStatus);
			update.setUpdateDate(new Date());
			update.setUpdateUserCode(systemUpdateResourceStatusParam.getOperatorCode());
			systemResourceMapper.updateWithoutNull(update);
			
		}
		
	}
	
	@Valid
	@Transactional
	public void exchangeResourceOrder(SystemExchangeResourceOrderParam systemExchangeResourceOrderParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		if (ROOT.getNodeCode().equalsIgnoreCase(systemExchangeResourceOrderParam.getNodeCodeA())) {
			throw new RuntimeException("nodeCode shouldn't be root");
		}
		
		if (ROOT.getNodeCode().equalsIgnoreCase(systemExchangeResourceOrderParam.getNodeCodeB())) {
			throw new RuntimeException("nodeCode shouldn't be root");
		}
		
		SystemResourceDomain nodeA = querySystemResourceByNodeCode(systemExchangeResourceOrderParam.getNodeCodeA());
		if (nodeA == null){
			throw new SystemResourceNotFoundRuntimeException();
		}		
		
		SystemResourceDomain nodeB = querySystemResourceByNodeCode(systemExchangeResourceOrderParam.getNodeCodeB());
		if (nodeB == null){
			throw new SystemResourceNotFoundRuntimeException();
		}
		
		if (!nodeA.getPid().equals(nodeB.getPid())){
			throw new SystemResourceHasDifferentParentRuntimeException();
		}
		
		SystemResourceDomain update = new SystemResourceDomain();
		update.setId(nodeA.getId());
		update.setObjectOrder(nodeB.getObjectOrder());
		update.setUpdateDate(new Date());
		update.setUpdateUserCode(systemExchangeResourceOrderParam.getOperatorCode());
		systemResourceMapper.updateWithoutNull(update);
		
		update.setId(nodeB.getId());
		update.setObjectOrder(nodeA.getObjectOrder());
		systemResourceMapper.updateWithoutNull(update);
	}
}
