package com.hawk.framework.utility.tools;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class CodecTools {
	
	/**
	 * 二进制数组转换成16进制的字符串
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b){
		return Hex.encodeHexString(b);
	}
	
	/**
	 * 16进制的字符串转换成2进制数组
	 * @param str
	 * @return
	 */
	public static byte[] toByteFronHexString (String str){
		try {
			return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}

}
