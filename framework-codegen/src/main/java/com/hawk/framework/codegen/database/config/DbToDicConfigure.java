package com.hawk.framework.codegen.database.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hawk.framework.utility.PackageTools;
import com.hawk.framework.utility.PackageTools.FileFilter;

/**
 * 
 * @author pzhang1
 *
 */
public class DbToDicConfigure implements IDbToDicConfigure {

	
	public String findSynonymCode(String columnName) {
		return synonymMap.get(columnName);
				 
	}
	
	public String findId(String code){
		return wordIdMap.get(code);
	}

	
	/**
	 * 所有的code集合
	 */
	private Map<String,String> wordIdMap = new HashMap<String,String>();

	/**
	 * 所有的同义词集合
	 * 
	 * @param packageName
	 * @return
	 */
	private Map<String, String> synonymMap = new HashMap<String, String>();

	private void fillWordIdMap(List<String> wordFileList) {
		for (String wordFile : wordFileList) {
			SAXReader saxReader = new SAXReader();
			InputStream inputStream = getClass().getResourceAsStream(wordFile);
			Document document;
			try {
				document = saxReader.read(inputStream);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			@SuppressWarnings("unchecked")
			Iterator<Element> it = document.getRootElement().elements().iterator();
			while(it.hasNext()){
				Element wordElement = it.next();
				String code = wordElement.elementTextTrim("code");
				String id = wordElement.elementTextTrim("id");
				wordIdMap.put(code,id);
			}
		}
	}

	private void fillSynonym(List<String> wordMapFileList) {
		for (String wordMapFile : wordMapFileList){
			SAXReader saxReader = new SAXReader();
			InputStream inputStream = getClass().getResourceAsStream(wordMapFile);
			Document document;
			try {
				document = saxReader.read(inputStream);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			@SuppressWarnings("unchecked")
			Iterator<Element> it =  document.getRootElement().elements().iterator();
			
			while(it.hasNext()){
				Element mapElement = it.next();
				String word = mapElement.elementTextTrim("word");
				synonymMap.put(word, word); //同义词包括自身
				@SuppressWarnings("unchecked")
				Iterator<Element> it2 =mapElement.element("synonymies").elements().iterator();
				while (it2.hasNext()){
					String synonym = it2.next().getTextTrim();
					synonymMap.put(synonym, word);
				}
			}
		}
	}

	public static DbToDicConfigure build(String packageName) {

		if (!packageName.endsWith("word")) {
			packageName = packageName.substring(0, packageName.lastIndexOf(".")) + "word";
		}

		/**
		 * 计算出所有的word文件
		 */
		List<String> wordFileList = new ArrayList<String>();
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(String fileName) {
				if (fileName.toLowerCase().endsWith(".word.xml"))
					return true;
				else
					return false;
			}
		};
		wordFileList.addAll(PackageTools.listFile(packageName, false, fileFilter));
		wordFileList.addAll(PackageTools.listFile("com.hawk.framework.codegen.word", false, fileFilter));

		/**
		 * 计算出所有的word.map文件
		 */
		List<String> wordMapFileList = new ArrayList<String>();
		fileFilter = new FileFilter() {
			@Override
			public boolean accept(String fileName) {
				if (fileName.toLowerCase().endsWith(".word.mapper.xml"))
					return true;
				else
					return false;
			}
		};
		wordMapFileList.addAll(PackageTools.listFile(packageName, false, fileFilter));
		wordMapFileList.addAll(PackageTools.listFile("com.hawk.framework.codegen.word", false, fileFilter));

		DbToDicConfigure dbToDicConfigure = new DbToDicConfigure();

		dbToDicConfigure.fillWordIdMap(wordFileList);
		dbToDicConfigure.fillSynonym(wordMapFileList);

		

		return dbToDicConfigure;
	}

}
