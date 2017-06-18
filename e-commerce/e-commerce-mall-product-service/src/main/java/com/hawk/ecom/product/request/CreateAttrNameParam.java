package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

/**
 * 创建产品属性
 * @author Administrator
 *
 */
public class CreateAttrNameParam {



	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPvid() {
		return pvid;
	}

	public void setPvid(Integer  pvid) {
		this.pvid = pvid;
	}

	public String getAttrNameCode() {
		return attrNameCode;
	}

	public void setAttrNameCode(String attrNameCode) {
		this.attrNameCode = attrNameCode;
	}

	public Integer getAttrNameBusinessType() {
		return attrNameBusinessType;
	}

	public void setAttrNameBusinessType(Integer attrNameBusinessType) {
		this.attrNameBusinessType = attrNameBusinessType;
	}

	public Integer getAttrValueType() {
		return attrValueType;
	}

	public void setAttrValueType(Integer attrValueType) {
		this.attrValueType = attrValueType;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Integer isSearch) {
		this.isSearch = isSearch;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 产品目录主键 category_id
	 */
	@NotNull
	private Integer categoryId;
	
	/**
	 * 父属性名主键 pid
	 */
	@NotNull
	private Integer pid;
	
	/**
	 * 父属性值主键 pvid
	 */
	@NotNull
	private Integer pvid;
	
	/**
	 * 属性名编号 attr_name_code
	 */
	private String attrNameCode;
	
	/**
	 * 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 */
	@NotNull
	private Integer attrNameBusinessType;
	
	
	
	/**
	 * 属性值类型 attr_value_type
	 */
	@NotNull
	private Integer attrValueType;
	
	/**
	 * 属性名名称 attr_name
	 */
	@NotEmpty
	private String attrName;
	
	/**
	 * 是否搜索 is_search
	 */
	@NotNull
	private Integer isSearch;	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
