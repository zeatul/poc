package com.hawk.framework.dic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.persist.domain.WordDomain;
import com.hawk.framework.dic.persist.mapper.WordMapper;

@Service
public class WordService {

	@Autowired
	private WordMapper wordMapper;
	
	
	public List<WordDomain> loadWordDomain(String systemCode,int version){
		
	}
}
