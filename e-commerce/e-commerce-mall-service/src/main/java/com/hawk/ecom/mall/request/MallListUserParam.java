package com.hawk.ecom.mall.request;

import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.pub.sql.PageParam;

public class MallListUserParam {
	
	
	
	public PageParam getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam pageParam) {
		this.pageParam = pageParam;
	}

	@NotNull(name="分页参数")
	private PageParam pageParam;

}
