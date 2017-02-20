package com.hawk.framework.dic.data;

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
		reserveldWordList.add("id");
		reserveldWordList.add("updt");
		reserveldWordList.add("stdt");
		reserveldWordList.add("endt");
		reserveldWordList.add("version");
		reserveldWordList.add("objectId");
		reserveldWordList.add("objectLabel");
		reserveldWordList.add("label");
		reserveldWordList.add("objectName");
	}
}
