package com.hawk.framework.codegen.database.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.service.ParseXmlService;
import com.hawk.framework.utility.tools.ClassPathTools;
import com.hawk.framework.utility.tools.PackageTools;
import com.hawk.framework.utility.tools.PackageTools.FileFilter;

/**
 * 
 * @author pzhang1
 *
 */
public class DbToDicConfigure implements IDbToDicConfigure {
	
		
	public Set<String> getWordFiles() {
		return wordFiles;
	}

	public String getWordProjectName() {
		return wordProjectName;
	}

	public void setWordProjectName(String wordProjectName) {
		this.wordProjectName = wordProjectName;
	}

	public String getWordPackage() {
		return wordPackage;
	}

	public void setWordPackage(String wordPackage) {
		this.wordPackage = wordPackage;
	}

	public String findSynonymCode(String columnName) {
		return synonymMap.get(columnName);
				 
	}
	
	@Override
	public Word findWord(String code) {
		return wordCodeMap.get(code);
	}

	
	/**
	 * 所有的code集合
	 */
	private Map<String,Word> wordCodeMap = new HashMap<String,Word>();
	
	/**
	 * 所有的word定义文件集合
	 */
	private Set<String> wordFiles ;

	/**
	 * 所有的同义词集合
	 * 
	 * @param packageName
	 * @return
	 */
	private Map<String, String> synonymMap = new HashMap<String, String>();

	private void fillWordCodeMap(String... packageNames) {
		try {
			ParseXmlService serice = new ParseXmlService();
			this.wordFiles = serice.listWordFile(packageNames);
			List<Word> wordList = serice.parseWordByClassPath(this.wordFiles.toArray(new String[]{})) ;
			for (Word word : wordList){
				wordCodeMap.put(word.getCode(), word);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void fillSynonym(Set<String> wordMapFileList) {
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
		DbToDicConfigure dbToDicConfigure = new DbToDicConfigure();
		String propertyFileClassPath = ClassPathTools.dotToAbsoluteClassPath(packageName.substring(0, packageName.lastIndexOf(".")))+"/dbToDic.properties";
		Properties props = new Properties();
		InputStream in = DatabaseConfigure.class.getResourceAsStream(propertyFileClassPath);
		if (in == null){
			throw new RuntimeException("Couldn't open stream = "+propertyFileClassPath);
		}
		in = new BufferedInputStream(in);
		
		
		try {
			props.load(in);
			dbToDicConfigure.setWordProjectName(props.getProperty("wordProjectName"));
			dbToDicConfigure.setWordPackage(props.getProperty("wordPackage"));			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		

		dbToDicConfigure.fillWordCodeMap(dbToDicConfigure.getWordPackage(),"com.hawk.framework.word");

		/**
		 * 计算出所有的word.map文件
		 */
		Set<String> wordMapFileSet = new HashSet<String>();
		
		FileFilter fileFilter = new ParseXmlService.wordMapFileFilter();
		
		wordMapFileSet.addAll(PackageTools.listFile(false, fileFilter,"com.hawk.framework.word",dbToDicConfigure.getWordPackage()));
		
		dbToDicConfigure.fillSynonym(wordMapFileSet);
		

		
		

		

		return dbToDicConfigure;
	}

	
	
	private String wordProjectName;
	
	private String wordPackage;

	

}
