package com.hawk.ecom.svp.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class UnicomNotifyParam {
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@NotEmpty("charge_task_code")
	private String taskId;
	
	@NotNull("last_exec_err_code")
	private Integer code;
	
	@NotEmpty("last_exec_err_msg")	
	private String desc;

}
