package com.hawk.ecom.muser.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 商城权限表
 * The class is mapped to the table t_msr_mall_right 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MallRightDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 父ID pid
	 */
	private Integer pid;
	
	/**
	 * 权限编号 right_code
	 */
	private String rightCode;
	
	/**
	 * 权限名称 right_name
	 */
	private String rightName;
	
	/**
	 * 主键PATH id_path
	 */
	private String idPath;
	
	/**
	 * 名称PATH name_path
	 */
	private String namePath;
	
	/**
	 * 编号PATH code_path
	 */
	private String codePath;
	
	/**
	 * 深度 depth
	 */
	private Integer depth;
	
	/**
	 * 系统保留 is_reserved
	 */
	private Integer isReserved;
	
	/**
	 * 创建者 create_user_code
	 */
	private String createUserCode;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新者 update_user_code
	 */
	private String updateUserCode;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除者 delete_user_code
	 */
	private String deleteUserCode;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
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
	
	/**
	 * 
	 * @return 父ID pid
	 */
	public Integer getPid(){
		return pid;
	}
	
	/**
	 * 
	 * @param pid 父ID pid
	 */	
	public void setPid (Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 
	 * @return 权限编号 right_code
	 */
	public String getRightCode(){
		return rightCode;
	}
	
	/**
	 * 
	 * @param rightCode 权限编号 right_code
	 */	
	public void setRightCode (String rightCode) {
		this.rightCode = rightCode;
	}
	
	/**
	 * 
	 * @return 权限名称 right_name
	 */
	public String getRightName(){
		return rightName;
	}
	
	/**
	 * 
	 * @param rightName 权限名称 right_name
	 */	
	public void setRightName (String rightName) {
		this.rightName = rightName;
	}
	
	/**
	 * 
	 * @return 主键PATH id_path
	 */
	public String getIdPath(){
		return idPath;
	}
	
	/**
	 * 
	 * @param idPath 主键PATH id_path
	 */	
	public void setIdPath (String idPath) {
		this.idPath = idPath;
	}
	
	/**
	 * 
	 * @return 名称PATH name_path
	 */
	public String getNamePath(){
		return namePath;
	}
	
	/**
	 * 
	 * @param namePath 名称PATH name_path
	 */	
	public void setNamePath (String namePath) {
		this.namePath = namePath;
	}
	
	/**
	 * 
	 * @return 编号PATH code_path
	 */
	public String getCodePath(){
		return codePath;
	}
	
	/**
	 * 
	 * @param codePath 编号PATH code_path
	 */	
	public void setCodePath (String codePath) {
		this.codePath = codePath;
	}
	
	/**
	 * 
	 * @return 深度 depth
	 */
	public Integer getDepth(){
		return depth;
	}
	
	/**
	 * 
	 * @param depth 深度 depth
	 */	
	public void setDepth (Integer depth) {
		this.depth = depth;
	}
	
	/**
	 * 
	 * @return 系统保留 is_reserved
	 */
	public Integer getIsReserved(){
		return isReserved;
	}
	
	/**
	 * 
	 * @param isReserved 系统保留 is_reserved
	 */	
	public void setIsReserved (Integer isReserved) {
		this.isReserved = isReserved;
	}
	
	/**
	 * 
	 * @return 创建者 create_user_code
	 */
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	/**
	 * 
	 * @param createUserCode 创建者 create_user_code
	 */	
	public void setCreateUserCode (String createUserCode) {
		this.createUserCode = createUserCode;
	}
	
	/**
	 * 
	 * @return 创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新者 update_user_code
	 */
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	/**
	 * 
	 * @param updateUserCode 更新者 update_user_code
	 */	
	public void setUpdateUserCode (String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}
	
	/**
	 * 
	 * @return 更新日期 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新日期 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除者 delete_user_code
	 */
	public String getDeleteUserCode(){
		return deleteUserCode;
	}
	
	/**
	 * 
	 * @param deleteUserCode 删除者 delete_user_code
	 */	
	public void setDeleteUserCode (String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
	}
	
	/**
	 * 
	 * @return 删除日期 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除日期 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	


}
