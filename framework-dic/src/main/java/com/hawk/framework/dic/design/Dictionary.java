package com.hawk.framework.dic.design;

import java.util.List;

import com.hawk.framework.dic.design.data.Word;

public class Dictionary {	
	
	public List<Word> getWordList() {
		return wordList;
	}
	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}
	public List<Application> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	
	private List<Application> applicationList;
	private List<Word> wordList ;

}
