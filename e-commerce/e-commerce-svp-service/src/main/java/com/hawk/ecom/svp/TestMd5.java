package com.hawk.ecom.svp;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;

public class TestMd5 {
	
	public static void main(String[] args){
		System.out.println(DigestUtils.md5Hex("hello"));
	}

}
