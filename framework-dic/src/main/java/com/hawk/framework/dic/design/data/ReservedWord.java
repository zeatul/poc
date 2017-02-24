package com.hawk.framework.dic.design.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 保留字
 * @author pzhang1
 *
 */
public class ReservedWord {

	static List<String> reserveldWordList = new ArrayList<String>();
	
	static{
		
		reserveldWordList.add("updt");
		reserveldWordList.add("stdt");
		reserveldWordList.add("endt");
		
		reserveldWordList.add("version");
		
		reserveldWordList.add("object_id");
		reserveldWordList.add("object_code");		
		reserveldWordList.add("object_name");
		reserveldWordList.add("comment");
		
		reserveldWordList.add("id");
		reserveldWordList.add("code");
		reserveldWordList.add("name");
		reserveldWordList.add("comment");
		
		reserveldWordList.add("create_date");
		reserveldWordList.add("update_date");
		reserveldWordList.add("delete_date");
		
		reserveldWordList.add("uuid");
		
		reserveldWordList.add("password");
	}
}
