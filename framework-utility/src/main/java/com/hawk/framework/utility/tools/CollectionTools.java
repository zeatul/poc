package com.hawk.framework.utility.tools;

import java.util.Collection;
import java.util.Map;

public class CollectionTools {
	
	public static boolean isNullOrEmpty(Collection<?> collecion){
		if (collecion == null)
			return true;
		else if (collecion.size() == 0)
			return true;
		else 
			return false;
	}
	
	public static boolean isNullOrEmpty(Map<?,?> map){
		if (map == null)
			return true;
		else if (map.size() == 0)
			return true;
		else 
			return false;
	}
	
	public static boolean isNotNullOrEmpty(Map<?,?> map){
		return (!isNullOrEmpty(map));
	}
	
	public static boolean isNotNullOrEmpty(Collection<?> collecion){
		return !isNullOrEmpty(collecion);
	}

}
