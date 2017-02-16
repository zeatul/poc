package com.hawk.framework.dic.database;

import com.hawk.framework.dic.data.DataDefinition;

/**
 * 
 * @author pzhang1
 *
 */
public class Column {

	public DataDefinition getDataDefinition() {
		return dataDefinition;
	}

	public void setDataDefinition(DataDefinition dataDefinition) {
		this.dataDefinition = dataDefinition;
	}

	private DataDefinition dataDefinition;

}
