package com.hawk.framework.dic.database;

import java.util.List;

public class Schema {
	
	

	public List<Application> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}

	private List<Application> applicationList; 

}
