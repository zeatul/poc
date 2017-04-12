package com.hawk.ecom.svp.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 联通给的加密类
 * @author Administrator
 *
 */
public class DES {
	private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static String encryptDES(String encryptString, String encryptKey, char[] legalChars) throws Exception {
		
		byte[] encryptedData = encrypt(encryptString, encryptKey);
		return Base64.encode(encryptedData, legalChars);
	}

	public static String encryptDES(String encryptString, String encryptKey) throws Exception {
		byte[] encryptedData = encrypt(encryptString, encryptKey);
		return Base64.encode(encryptedData);
	}
    private static byte[] encrypt(String encryptString, String encryptKey){
    	
    	byte[] encryptedData = null;
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			encryptedData = cipher.doFinal(encryptString.getBytes("utf-8"));
		} catch (Exception e) {

		} 
		return encryptedData;
    }
	public static String decryptDES(String decryptString, String decryptKey) {
		try {
			byte[] byteMi = Base64.decode(decryptString);
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte decryptedData[] = cipher.doFinal(byteMi);
			return new String(decryptedData);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) throws Exception {
		/*
		 * String plaintext =
		 * "{\"consumer\":\"18602031793\",\"createTime\":\"\",\"description\":\"\",\"finished\":false,\"flowType\":0,\"id\":\"\",\"lastProcessedTime\":\"\",\"occupied\":false,\"orderid\":\"\",\"productCode\":\"\",\"productName\":\"中华好诗词\",\"productType\":\"\",\"remarks\":\"nice\",\"stage\":0,\"status\":\"\",\"title\":\"\"}";
		 * String key = "#$%&DESA";
		 * 
		 * String ciphertext = DES.encryptDES(plaintext, key);
		 * //System.out.println("明文：" + plaintext); //System.out.println("密钥：" +
		 * "17w%h=ex"); System.out.println("密文：" + ciphertext);
		 * //System.out.println("解密后："+DES.decryptDES(ciphertext, key));
		 * //System.out.println("=====================");
		 */

	}
}
