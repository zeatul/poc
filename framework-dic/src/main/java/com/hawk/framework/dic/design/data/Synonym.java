package com.hawk.framework.dic.design.data;

public class Synonym {
	
	public String getSynonymDisplayName() {
		return synonymDisplayName;
	}
	public void setSynonymDisplayName(String synonymDisplayName) {
		this.synonymDisplayName = synonymDisplayName;
	}
	public String getOriginCode() {
		return originCode;
	}
	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}
	public String getSynonymCode() {
		return synonymCode;
	}
	public void setSynonymCode(String synonymCode) {
		this.synonymCode = synonymCode;
	}
	public String getSynonymType() {
		return synonymType;
	}
	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}
	private String originCode;
	private String synonymCode;
	private String synonymType;
	private String synonymDisplayName;

}
