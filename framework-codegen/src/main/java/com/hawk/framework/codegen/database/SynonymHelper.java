package com.hawk.framework.codegen.database;

import java.util.HashMap;
import java.util.Map;

import com.hawk.framework.dic.design.constant.ConstSynonymType;
import com.hawk.framework.dic.design.data.Synonym;

public class SynonymHelper {
	
	private final static Map<String,Synonym> synonymMap = new HashMap<String,Synonym>();
	static{
		addWord("object_id","table_object_id",null);
		addWord("object_id","fk_object_id",null);
		addWord("object_id","parent_column_object_id",null);
		addWord("object_id","child_column_object_id",null);
		addWord("object_id","word_object_id",null);
		addWord("object_id","index_object_id",null);
		addWord("object_id","column_object_id",null);
		addWord("object_id","application_object_id",null);
		addWord("object_id","parent_table_object_id",null);
		addWord("object_id","table_object_id",null);
		addWord("object_id","child_table_object_id",null);
	}
	


	
	public static Synonym findSynonym(String code){
		return synonymMap.get(code);
	}
	
	private static void addWord(String originCode ,String synonymCode,String synonymDisplayName){
		Synonym synonym = new Synonym();
		synonym.setOriginCode(originCode);
		synonym.setSynonymCode(synonymCode);
		synonym.setSynonymType(ConstSynonymType.WORD);
		synonym.setSynonymDisplayName(synonymDisplayName);
		
		synonymMap.put(synonymCode,synonym);
	}
	

}
