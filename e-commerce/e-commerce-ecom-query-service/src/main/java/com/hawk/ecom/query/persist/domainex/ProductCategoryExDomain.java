package com.hawk.ecom.query.persist.domainex;

public class ProductCategoryExDomain {


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	/**
	 * 主键 id
	 */
	private Integer id;
	/**
	 * 父ID pid
	 */
	private Integer pid;
	/**
	 * 产品目录名称 category_name
	 */
	private String categoryName;
	/**
	 * 是否为最终产品目录分类，最终产品目录分类才能有商品模板 is_leaf
	 */
	private Integer isLeaf;
	

	

}
