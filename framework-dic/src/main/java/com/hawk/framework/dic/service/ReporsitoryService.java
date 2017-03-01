package com.hawk.framework.dic.service;

import java.util.ArrayList;
import java.util.List;

import com.hawk.framework.dic.design.Application;
import com.hawk.framework.dic.design.Dictionary;
import com.hawk.framework.dic.design.data.DataDefinition;
import com.hawk.framework.dic.domain.ApplicationDomain;
import com.hawk.framework.dic.domain.DataDefinitionDomain;
import com.hawk.framework.utility.DomainTools;

public class ReporsitoryService {
	
	/**
	 * 存到当前库
	 */
	public void saveToDatabase(Dictionary dictionary){
		/**
		 * 校验一下dictionary的逻辑完整性
		 */
		
		
		/**
		 * 转换 data_definition 为 入库记录
		 */
		List<DataDefinitionDomain> dataDefinitionDomainList = new ArrayList<DataDefinitionDomain>();
		for(DataDefinition dataDefinition : dictionary.getDataDefinitionList()){
			DataDefinitionDomain dataDefinitionDomain = new DataDefinitionDomain();
			DomainTools.copy(dataDefinition, dataDefinitionDomain);
		}
		
		
		/**
		 * 转换 表，字段，索引，索引字段 ，外键，外键字段匹配为入库记录
		 * 转换应用，应用的表为入库记录
		 */
		for (Application application : dictionary.getApplicationList()){
			ApplicationDomain applicationDomain = new ApplicationDomain();
			DomainTools.copy(application, applicationDomain);
		}
		
		
		
	}
	
	/**
	 * 存为xml
	 */
	public void saveToXml(Dictionary dictionary){
		
	}
	
	/**
	 * 存为历史数据库
	 */
	public void saveToHistoryDatabase(Dictionary dictionary){
		
	}
	
	/**
	 * 存为历史xml文件
	 */
	public void saveToHistoryXml(Dictionary dictionary){
		
	}

}
