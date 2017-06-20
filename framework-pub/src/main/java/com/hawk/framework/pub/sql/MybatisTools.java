package com.hawk.framework.pub.sql;

import java.util.List;

public class MybatisTools {

	public static <T> T single(List<T> list){
		if (list == null)
			return null;
		if(list.size() == 0)
			return null;
		if (list.size() >1)
			throw new RuntimeException("list has more than 1 records");
		
		return list.get(0);
	}
	
	/**
	 * 注入分页参数
	 * @param mybatisParam
	 * @param pageParam
	 * @return
	 */
	public static MybatisParam page(MybatisParam mybatisParam , PageParam pageParam){
		if (pageParam == null)
			return mybatisParam;
		int pageIndex = 1;
		if (pageParam.getPageIndex() != null &&  pageParam.getPageIndex() > 0){
			pageIndex = pageParam.getPageIndex();
		}
		
		int pageRowCount = 10;
		if (pageParam.getPageRowCount() != null && pageParam.getPageRowCount() > 0){
			pageRowCount =pageParam.getPageRowCount();
		}
		
		if (pageRowCount > 500)
			throw new RuntimeException("每页不能超过500条记录");
		
		int offset = (pageIndex-1)*pageRowCount;
		int limit = pageRowCount;
		
		mybatisParam.put("_offset", offset);
		mybatisParam.put("_limit", limit);
		mybatisParam.put("_orderby", pageParam.getOrder());
		return mybatisParam;
		
	}
}
