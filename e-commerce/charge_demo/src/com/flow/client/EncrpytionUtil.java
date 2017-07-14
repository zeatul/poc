package com.flow.client;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DES加解密类
 */
public class EncrpytionUtil {
	public static String suffix = "PIEncrypt";

	private static byte[] salt = { (byte) 84, (byte) 94, (byte) 196,
			(byte) 118, (byte) 67, (byte) 236, (byte) 201, (byte) 186 };

	private static byte[] iv = { (byte) 79, (byte) 76, (byte) 136, (byte) 220,
			(byte) 195, (byte) 184, (byte) 91, (byte) 50 };

	// 以下用于MD5加密
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * MD5加密后的数组变成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 字符串进行MD5加密
	 * 
	 * @param str
	 * @return MD5加密后进行16进制编码
	 */
	public static String str2MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byteArrayToHexString(md.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("不支持MD5算法");
		}
	}

	/**
	 * 字符串进行MD5加密
	 * 
	 * @param str
	 * @return MD5加密后的字节数组
	 */
	public static byte[] str2MD5Bytes(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("不支持MD5算法");
		}
	}

	/**
	 * 将一段字符串加密。
	 * 
	 * @param p_value
	 *            将加密的字符串。
	 * @return 经过加密的字符串。
	 */
	public static String encryptString(String p_value) {
		try {
			Cipher ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ivp = new IvParameterSpec(iv);
			DESKeySpec desKeySpec = new DESKeySpec(salt);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			ecipher.init(Cipher.ENCRYPT_MODE, secretKey, ivp);

			byte[] utf8 = p_value.getBytes("UTF8");
			byte[] enc = ecipher.doFinal(utf8);
			// 2009-08-10 mqq Base64编码规范化
			return new String(Base64.encodeBase64(enc)) + suffix;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将加密的字符串解密。
	 * 
	 * @param p_value
	 *            将加密的字符串。
	 * @return 经过解密的字符串。
	 */
	public static String decryptString(String p_value) {
		try {
			Cipher dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ivp = new IvParameterSpec(iv);
			DESKeySpec desKeySpec = new DESKeySpec(salt);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			dcipher.init(Cipher.DECRYPT_MODE, secretKey, ivp);

			// 截除后缀
			p_value = p_value.substring(0, p_value.length() - suffix.length());
			// 2009-08-10 mqq Base64编码规范化
			byte[] dec = Base64.decodeBase64(p_value == null ? new byte[0]
					: p_value.getBytes());
			byte[] utf8 = dcipher.doFinal(dec);
			String result = new String(utf8, "UTF8");
			// 2007-6-27 Modified by yemin. 保留原始数据信息。
			// result = result.replace("\r\n", "");
			if (result.endsWith("\r\n"))
				result = result.substring(0, result.length() - 2);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密 为前端加密的串进行解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param keyseed
	 *            解密密钥
	 * @return 解密字符串
	 * @throws Exception
	 */
	public static String decryptByAESForJS(String content, String keyseed)
			throws Exception {
		SecretKeySpec key = getKeySpecFromBytes(keyseed.toUpperCase());
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		String result = new String(cipher.doFinal(hex2byte(content
				.getBytes("UTF-8"))));
		return result.trim();

	}

	// 从十六进制字符串生成Key
	private static SecretKeySpec getKeySpecFromBytes(String strBytes)
			throws NoSuchAlgorithmException {
		SecretKeySpec spec = new SecretKeySpec(hex2byte(strBytes.getBytes()),
				"AES");
		return spec;
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数!");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 将16进制字符串转化成byte数组
	 * 
	 * @param str
	 *            所要转化的字符串
	 * @return 转化后的byte[]
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] hex2byte(String str) {
		try {
			byte[] b = str.getBytes("UTF-8");
			if ((b.length % 2) != 0)
				throw new IllegalArgumentException("长度不是偶数");
			byte[] b2 = new byte[b.length / 2];
			for (int n = 0; n < b.length; n += 2) {
				String item = new String(b, n, 2);
				b2[n / 2] = (byte) Integer.parseInt(item, 16);
			}
			return b2;
		} catch (Exception e) {
			return null;
		}
	}

}
