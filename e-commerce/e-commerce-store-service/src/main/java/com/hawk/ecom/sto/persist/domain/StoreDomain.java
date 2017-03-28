package com.hawk.ecom.sto.persist.domain;
import java.io.Serializable;




/**
 * 商铺
 * The class is mapped to the table t_sto_store 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class StoreDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 *  store_name
	 */
	private String storeName;
	
	/**
	 *  store_status
	 */
	private String storeStatus;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 商户编号 store_code
	 */
	public String getStoreCode(){
		return storeCode;
	}
	
	/**
	 * 
	 * @param storeCode 商户编号 store_code
	 */	
	public void setStoreCode (String storeCode) {
		this.storeCode = storeCode;
	}
	
	/**
	 * 
	 * @return  store_name
	 */
	public String getStoreName(){
		return storeName;
	}
	
	/**
	 * 
	 * @param storeName  store_name
	 */	
	public void setStoreName (String storeName) {
		this.storeName = storeName;
	}
	
	/**
	 * 
	 * @return  store_status
	 */
	public String getStoreStatus(){
		return storeStatus;
	}
	
	/**
	 * 
	 * @param storeStatus  store_status
	 */	
	public void setStoreStatus (String storeStatus) {
		this.storeStatus = storeStatus;
	}
	


}
