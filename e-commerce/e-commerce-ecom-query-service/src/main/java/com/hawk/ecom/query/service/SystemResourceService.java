package com.hawk.ecom.query.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pub.constant.ConstSystemResource;
import com.hawk.ecom.query.persist.domainex.SystemResourceExDomain;
import com.hawk.ecom.query.persist.mapperex.SystemResourceExMapper;

@Service
public class SystemResourceService {

	@Autowired
	private SystemResourceExMapper systemResourceExMapper;

	public List<SystemResourceExDomain> h5main() {
		List<SystemResourceExDomain> list = systemResourceExMapper.h5main();
		if (list.size() == 0)
			return list;

		List<SystemResourceExDomain> result = new ArrayList<SystemResourceExDomain>();
		Map<Integer, Object> filterMap = new HashMap<Integer, Object>();
		int flag = list.get(0).getDepth();

		for (SystemResourceExDomain systemResourceExDomain : list) {
			if (systemResourceExDomain.getDepth() == flag) { //第一层节点，只需要状态是激活
				if (systemResourceExDomain.getNodeStatus() == ConstSystemResource.NodeStatus.ACTIVATED) {
					filterMap.put(systemResourceExDomain.getId(), systemResourceExDomain);
					result.add(systemResourceExDomain);
				}
			}else{//大于第一层的节点，需要过滤调 父节点不是激活状态的数据
				if (systemResourceExDomain.getNodeStatus() == ConstSystemResource.NodeStatus.ACTIVATED && filterMap.containsKey(systemResourceExDomain.getPid())){ 
					filterMap.put(systemResourceExDomain.getId(), systemResourceExDomain);
					result.add(systemResourceExDomain);
				}
			}
		}

		return result;
	}
}
