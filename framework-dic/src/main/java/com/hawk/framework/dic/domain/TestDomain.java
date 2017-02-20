package com.hawk.framework.dic.domain;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;




/**
 * 测试表的元数据使用,包括字段类型，索引，外键
 * The class is mapped to the table t_dic_test
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class TestDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*col1 col1*/
	private String col1;
	
	/*col2 col2*/
	private String col2;
	
	/* col3*/
	private Date col3;
	
	/* col4*/
	private BigDecimal col4;
	
	/* col5*/
	private BigDecimal col5;
	
	
	/**
	 * 
	 * @return col1 col1
	 */
	public String getCol1(){
		return col1;
	}
	
	/**
	 * 
	 * @param col1 col1 col1
	 */	
	public void setCol1 (String col1) {
		this.col1 = col1;
	}
	
	/**
	 * 
	 * @return col2 col2
	 */
	public String getCol2(){
		return col2;
	}
	
	/**
	 * 
	 * @param col2 col2 col2
	 */	
	public void setCol2 (String col2) {
		this.col2 = col2;
	}
	
	/**
	 * 
	 * @return  col3
	 */
	public Date getCol3(){
		return col3;
	}
	
	/**
	 * 
	 * @param col3  col3
	 */	
	public void setCol3 (Date col3) {
		this.col3 = col3;
	}
	
	/**
	 * 
	 * @return  col4
	 */
	public BigDecimal getCol4(){
		return col4;
	}
	
	/**
	 * 
	 * @param col4  col4
	 */	
	public void setCol4 (BigDecimal col4) {
		this.col4 = col4;
	}
	
	/**
	 * 
	 * @return  col5
	 */
	public BigDecimal getCol5(){
		return col5;
	}
	
	/**
	 * 
	 * @param col5  col5
	 */	
	public void setCol5 (BigDecimal col5) {
		this.col5 = col5;
	}
	


}
