package com.hawk.framework.dic.design;

import java.util.List;

import com.hawk.framework.dic.design.data.Word;

public class Dictionary {	
	
	public List<Application> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	public List<Word> getDataDefinitionList() {
		return dataDefinitionList;
	}
	public void setDataDefinitionList(List<Word> dataDefinitionList) {
		this.dataDefinitionList = dataDefinitionList;
	}
	private List<Application> applicationList;
	private List<Word> dataDefinitionList ;

}
