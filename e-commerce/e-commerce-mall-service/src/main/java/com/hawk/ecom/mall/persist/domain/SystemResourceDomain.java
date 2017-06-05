package com.hawk.ecom.mall.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 导航类资源管理
 * The class is mapped to the table t_mal_system_resource 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SystemResourceDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 父ID pid
	 */
	private Long pid;
	
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
	 * 节点编号 node_code
	 */
	private String nodeCode;
	
	/**
	 * 系统保留 reserved
	 */
	private Integer reserved;
	
	/**
	 * 节点名称 node_name
	 */
	private String nodeName;
	
	/**
	 * 节点类型 node_type
	 */
	private Integer nodeType;
	
	/**
	 * 节点子类型 node_sub_type
	 */
	private Integer nodeSubType;
	
	/**
	 * 节点值类型 node_value_type
	 */
	private Integer nodeValueType;
	
	/**
	 * 节点值 node_value
	 */
	private String nodeValue;
	
	/**
	 * 节点序号 object_order
	 */
	private Integer objectOrder;
	
	/**
	 * 节点状态 node_status
	 */
	private Integer nodeStatus;
	
	/**
	 * 节点描述 node_desc
	 */
	private String nodeDesc;
	
	/**
	 * 节点图标 node_ico
	 */
	private String nodeIco;
	
	/**
	 * 节点选中图标 node_checked_ico
	 */
	private String nodeCheckedIco;
	
	/**
	 * 节点禁用图标 node_grey_ico
	 */
	private String nodeGreyIco;
	
	/**
	 * 节点鼠标浮动图标 node_rise_ico
	 */
	private String nodeRiseIco;
	
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
	 * @return 父ID pid
	 */
	public Long getPid(){
		return pid;
	}
	
	/**
	 * 
	 * @param pid 父ID pid
	 */	
	public void setPid (Long pid) {
		this.pid = pid;
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
	 * @return 节点编号 node_code
	 */
	public String getNodeCode(){
		return nodeCode;
	}
	
	/**
	 * 
	 * @param nodeCode 节点编号 node_code
	 */	
	public void setNodeCode (String nodeCode) {
		this.nodeCode = nodeCode;
	}
	
	/**
	 * 
	 * @return 系统保留 reserved
	 */
	public Integer getReserved(){
		return reserved;
	}
	
	/**
	 * 
	 * @param reserved 系统保留 reserved
	 */	
	public void setReserved (Integer reserved) {
		this.reserved = reserved;
	}
	
	/**
	 * 
	 * @return 节点名称 node_name
	 */
	public String getNodeName(){
		return nodeName;
	}
	
	/**
	 * 
	 * @param nodeName 节点名称 node_name
	 */	
	public void setNodeName (String nodeName) {
		this.nodeName = nodeName;
	}
	
	/**
	 * 
	 * @return 节点类型 node_type
	 */
	public Integer getNodeType(){
		return nodeType;
	}
	
	/**
	 * 
	 * @param nodeType 节点类型 node_type
	 */	
	public void setNodeType (Integer nodeType) {
		this.nodeType = nodeType;
	}
	
	/**
	 * 
	 * @return 节点子类型 node_sub_type
	 */
	public Integer getNodeSubType(){
		return nodeSubType;
	}
	
	/**
	 * 
	 * @param nodeSubType 节点子类型 node_sub_type
	 */	
	public void setNodeSubType (Integer nodeSubType) {
		this.nodeSubType = nodeSubType;
	}
	
	/**
	 * 
	 * @return 节点值类型 node_value_type
	 */
	public Integer getNodeValueType(){
		return nodeValueType;
	}
	
	/**
	 * 
	 * @param nodeValueType 节点值类型 node_value_type
	 */	
	public void setNodeValueType (Integer nodeValueType) {
		this.nodeValueType = nodeValueType;
	}
	
	/**
	 * 
	 * @return 节点值 node_value
	 */
	public String getNodeValue(){
		return nodeValue;
	}
	
	/**
	 * 
	 * @param nodeValue 节点值 node_value
	 */	
	public void setNodeValue (String nodeValue) {
		this.nodeValue = nodeValue;
	}
	
	/**
	 * 
	 * @return 节点序号 object_order
	 */
	public Integer getObjectOrder(){
		return objectOrder;
	}
	
	/**
	 * 
	 * @param objectOrder 节点序号 object_order
	 */	
	public void setObjectOrder (Integer objectOrder) {
		this.objectOrder = objectOrder;
	}
	
	/**
	 * 
	 * @return 节点状态 node_status
	 */
	public Integer getNodeStatus(){
		return nodeStatus;
	}
	
	/**
	 * 
	 * @param nodeStatus 节点状态 node_status
	 */	
	public void setNodeStatus (Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
	
	/**
	 * 
	 * @return 节点描述 node_desc
	 */
	public String getNodeDesc(){
		return nodeDesc;
	}
	
	/**
	 * 
	 * @param nodeDesc 节点描述 node_desc
	 */	
	public void setNodeDesc (String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	
	/**
	 * 
	 * @return 节点图标 node_ico
	 */
	public String getNodeIco(){
		return nodeIco;
	}
	
	/**
	 * 
	 * @param nodeIco 节点图标 node_ico
	 */	
	public void setNodeIco (String nodeIco) {
		this.nodeIco = nodeIco;
	}
	
	/**
	 * 
	 * @return 节点选中图标 node_checked_ico
	 */
	public String getNodeCheckedIco(){
		return nodeCheckedIco;
	}
	
	/**
	 * 
	 * @param nodeCheckedIco 节点选中图标 node_checked_ico
	 */	
	public void setNodeCheckedIco (String nodeCheckedIco) {
		this.nodeCheckedIco = nodeCheckedIco;
	}
	
	/**
	 * 
	 * @return 节点禁用图标 node_grey_ico
	 */
	public String getNodeGreyIco(){
		return nodeGreyIco;
	}
	
	/**
	 * 
	 * @param nodeGreyIco 节点禁用图标 node_grey_ico
	 */	
	public void setNodeGreyIco (String nodeGreyIco) {
		this.nodeGreyIco = nodeGreyIco;
	}
	
	/**
	 * 
	 * @return 节点鼠标浮动图标 node_rise_ico
	 */
	public String getNodeRiseIco(){
		return nodeRiseIco;
	}
	
	/**
	 * 
	 * @param nodeRiseIco 节点鼠标浮动图标 node_rise_ico
	 */	
	public void setNodeRiseIco (String nodeRiseIco) {
		this.nodeRiseIco = nodeRiseIco;
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
