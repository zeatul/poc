package com.hawk.ecom.pub.srping.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.service.ParseXmlService;
import com.hawk.framework.dic.validation.ValidateService;
import com.hawk.framework.dic.validation.validator.NotEmptyValidator;
import com.hawk.framework.dic.validation.validator.NotNullValidator;
import com.hawk.framework.dic.validation.validator.SingleObjectValidator;

@Configuration
public class ValidateConfig {
	@Bean
	public NotEmptyValidator notEmptyValidator(){
		return new NotEmptyValidator();
	}
	
	@Bean	
	public DictionaryService dictionaryService(ParseXmlService parseXmlService) throws Exception{
		List<String> packageNames = new ArrayList<String>();
		packageNames.add("com.hawk.ecom.word");
		packageNames.add("com.hawk.framework.word");
		DictionaryService dictionaryService = new DictionaryService(packageNames,parseXmlService);
		return dictionaryService;
	}
	
	@Bean
	public NotNullValidator notNullValidator(){
		return new NotNullValidator();
	}
	
	@Bean
	public ParseXmlService parseXmlService(){
		return new ParseXmlService();
	}
	
	@Bean
	public SingleObjectValidator SingleObjectValidator(){
		return new SingleObjectValidator();
	}
	
	@Bean
	public ValidateService validateService(){
		return new ValidateService();
	}
}
