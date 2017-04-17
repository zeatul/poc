package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




/**
 * 代金券编号生成表
 * The class is mapped to the table t_svp_bsi_cash_coupon_sequence 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BsiCashCouponSequenceDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * stub stub
	 */
	private String stub;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	
	/**
	 * 
	 * @return stub stub
	 */
	public String getStub(){
		return stub;
	}
	
	/**
	 * 
	 * @param stub stub stub
	 */	
	public void setStub (String stub) {
		this.stub = stub;
	}
	
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
	


}
