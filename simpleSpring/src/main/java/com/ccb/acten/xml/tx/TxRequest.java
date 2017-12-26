package com.ccb.acten.xml.tx;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="TX")
public class TxRequest<K> {
	
	public TxHeader getTxHeader() {
		return txHeader;
	}

	public void setTxHeader(TxHeader txHeader) {
		this.txHeader = txHeader;
	}

	public TxBody<K> getTxBody() {
		return txBody;
	}

	public void setTxBody(TxBody<K> txBody) {
		this.txBody = txBody;
	}

	public TxEmb getTxEmb() {
		return txEmb;
	}

	public void setTxEmb(TxEmb txEmb) {
		this.txEmb = txEmb;
	}

	private TxHeader txHeader;
	
	private TxBody<K> txBody;
	
	private TxEmb txEmb;

}
