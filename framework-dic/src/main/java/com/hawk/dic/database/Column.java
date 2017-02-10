package com.hawk.dic.database;

import com.hawk.dic.data.DataDefinition;

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
