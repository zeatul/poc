package com.hawk.framework.utility.tools;

import java.util.Collection;

public class CollectionTools {
	
	public static boolean isNullOrEmpty(Collection<?> collecion){
		if (collecion == null)
			return true;
		else if (collecion.size() == 0)
			return true;
		else 
			return false;
	}
	
	public static boolean isNotNullOrEmpty(Collection<?> collecion){
		return !isNullOrEmpty(collecion);
	}

}
