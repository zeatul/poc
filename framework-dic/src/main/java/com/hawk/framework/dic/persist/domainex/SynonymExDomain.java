package com.hawk.framework.dic.persist.domainex;

public class SynonymExDomain {
	
	public String getOriginObjectCode() {
		return originObjectCode;
	}

	public void setOriginObjectCode(String originObjectCode) {
		this.originObjectCode = originObjectCode;
	}

	public String getSynonymObjectCode() {
		return synonymObjectCode;
	}

	public void setSynonymObjectCode(String synonymObjectCode) {
		this.synonymObjectCode = synonymObjectCode;
	}

	public String getSynonymDisplayName() {
		return synonymDisplayName;
	}

	public void setSynonymDisplayName(String synonymDisplayName) {
		this.synonymDisplayName = synonymDisplayName;
	}

	public String getSynonymType() {
		return synonymType;
	}

	public void setSynonymType(String synonymType) {
		this.synonymType = synonymType;
	}

	/**
	 * 原单词id origin_object_code
	 */
	private String originObjectCode;
	
	/**
	 * 同义词编码 synonym_object_code
	 */
	private String synonymObjectCode;
	
	/**
	 * 同义词显示名称 synonym_display_name
	 */
	private String synonymDisplayName;
	
	/**
	 * 同义词类型 synonym_type
	 */
	private String synonymType; 

}
