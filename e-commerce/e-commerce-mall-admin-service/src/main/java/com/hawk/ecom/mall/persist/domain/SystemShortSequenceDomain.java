package com.hawk.ecom.mall.persist.domain;
import java.io.Serializable;




/**
 * 商场短ID生成表
 * The class is mapped to the table t_mal_system_short_sequence 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SystemShortSequenceDomain implements Serializable {

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
