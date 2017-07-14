package com.hawk.framework.utility.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ResourceTools {
	
	public static List<String> readResource(String classpath, Class<?> clazz) {
		return readResource(classpath,clazz,false);
	}

	public static List<String> readResource(String classpath, Class<?> clazz,boolean includeEmpty) {
		InputStream in = null;
		BufferedInputStream bis = null;
		BufferedReader reader = null;
		try {
			List<String> list = new LinkedList<String>();
			in = clazz.getResourceAsStream(classpath);
			bis = new BufferedInputStream(in);
			reader = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024);
			while (reader.ready()) {
				String line = reader.readLine();
				if (!includeEmpty){
					if (StringTools.isNotNullOrEmpty(line)){
						list.add(line);	
					}
				}else{
					list.add(line);	
				}
				
				
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {

				}
			}
		}
	}

}
