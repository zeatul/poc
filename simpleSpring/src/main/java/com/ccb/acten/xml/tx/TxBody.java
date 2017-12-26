package com.ccb.acten.xml.tx;

public class TxBody<K> {
	
	public TxCommon getCommon() {
		return common;
	}

	public void setCommon(TxCommon common) {
		this.common = common;
	}

	public K getEntity() {
		return entity;
	}

	public void setEntity(K entity) {
		this.entity = entity;
	}

	private TxCommon common;
	
	private K entity;

}
