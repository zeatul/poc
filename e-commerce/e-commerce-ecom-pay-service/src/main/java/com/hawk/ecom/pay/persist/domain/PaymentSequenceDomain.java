package com.hawk.ecom.pay.persist.domain;
import java.io.Serializable;




/**
 * 支付单编号生成表
 * The class is mapped to the table t_pay_payment_sequence 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PaymentSequenceDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * stub stub
	 */
	private String stub;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	
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
	public Integer getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Integer id) {
		this.id = id;
	}
	


}
