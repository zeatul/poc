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
}
