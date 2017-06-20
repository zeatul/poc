package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 
 * The class is mapped to the table t_svp_supplier 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SupplierDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 供应商编号 supplier_code
	 */
	private String supplierCode;
	
	/**
	 * 供应商名称 supplier_name
	 */
	private String supplierName;
	
	
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
	 * @return 供应商编号 supplier_code
	 */
	public String getSupplierCode(){
		return supplierCode;
	}
	
	/**
	 * 
	 * @param supplierCode 供应商编号 supplier_code
	 */	
	public void setSupplierCode (String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	/**
	 * 
	 * @return 供应商名称 supplier_name
	 */
	public String getSupplierName(){
		return supplierName;
	}
	
	/**
	 * 
	 * @param supplierName 供应商名称 supplier_name
	 */	
	public void setSupplierName (String supplierName) {
		this.supplierName = supplierName;
	}
	


}
