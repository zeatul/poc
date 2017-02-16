package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 对值(Map<Key,Value>
 * The class is mapped to the table t_dic_pair
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PairDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*字典对象id data_definition_object_id*/
	private String dataDefinitionObjectId;
	
	/*key pair_key*/
	private String pairKey;
	
	/*value pair_value*/
	private String pairValue;
	
	
	/**
	 * 
	 * @return 字典对象id data_definition_object_id
	 */
	public String getDataDefinitionObjectId(){
		return dataDefinitionObjectId;
	}
	
	/**
	 * 
	 * @param dataDefinitionObjectId 字典对象id data_definition_object_id
	 */	
	public void setDataDefinitionObjectId (String dataDefinitionObjectId) {
		this.dataDefinitionObjectId = dataDefinitionObjectId;
	}
	
	/**
	 * 
	 * @return key pair_key
	 */
	public String getPairKey(){
		return pairKey;
	}
	
	/**
	 * 
	 * @param pairKey key pair_key
	 */	
	public void setPairKey (String pairKey) {
		this.pairKey = pairKey;
	}
	
	/**
	 * 
	 * @return value pair_value
	 */
	public String getPairValue(){
		return pairValue;
	}
	
	/**
	 * 
	 * @param pairValue value pair_value
	 */	
	public void setPairValue (String pairValue) {
		this.pairValue = pairValue;
	}
	


}
