package com.ccb.acten.xml;

import java.io.IOException;

import com.ccb.acten.xml.tx.TxRequest;
import com.ccb.acten.xml.vo.CreatePersonInVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class TestJacksonApp {
	
	public static void main(String[] args) throws Exception{
		XmlMapper xmlMapper = new XmlMapper();
		
		TxRequest<CreatePersonInVo> tx = xmlMapper.readValue("", new TypeReference<TxRequest<CreatePersonInVo>>() {});
	}

}
