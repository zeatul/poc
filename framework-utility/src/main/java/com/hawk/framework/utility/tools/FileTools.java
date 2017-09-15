package com.hawk.framework.utility.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class FileTools {

	/**
	 * 创建目录
	 * 
	 * @param dirPath
	 */
	public static void createDir(String dirPath) {
		File dir = new File(dirPath);
		if (dir.exists()) {
			if (dir.isDirectory())
				return;
			else {
				throw new RuntimeException("The creating dirPath is a file ,dirPath = " + dirPath);
			}
		}

		if (dir.mkdirs()) {
			dir.setReadable(true);
			dir.setWritable(true);
		}

		if (!dir.exists()) {
			throw new RuntimeException("Failed to create directory = " + dirPath);
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return;

		file.delete();

		if (file.exists()) {
			throw new RuntimeException("Failed to delete file = " + filePath);
		}
	}

	/**
	 * 读取文本文件的全部内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readTextFile(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			byte[] b = new byte[fis.available()];
			fis.read(b, 0, b.length);
			return new String(b, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static byte[] readBinaryFile(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			byte[] b = new byte[fis.available()];
			fis.read(b, 0, b.length);
			return b;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * 
	 * @param filePath
	 * @param list
	 *            存放读取结果
	 * @return 文件记录行数
	 */
	public static int readTextFile(String filePath, List<String> list) {
		return readFile(filePath, list);
	}

	/**
	 * 读取文本文件的行数
	 * 
	 * @param filePath
	 * @return
	 */
	public static int rowcount(String filePath) {
		return readFile(filePath, null);
	}

	private static int readFile(String filePath, List<String> list) {
		File file = new File(filePath);
		if (!file.exists())
			throw new RuntimeException("Could't find file = " + filePath);

		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedReader reader = null;

		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			reader = new BufferedReader(new InputStreamReader(bis, "utf-8"), 10 * 1024);
			int rowCount = 0;
			while (reader.ready()) {
				String line = reader.readLine();
				if (list != null) {
					list.add(line);
				}
				rowCount++;
			}
			return rowCount;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {

				}
			}
		}

	}

	public static void main(String[] args){
		String filePath = "C:/mydata/temp/apiclient_cert.p12";
		byte[] b = readBinaryFile(filePath);
		String hex = CodecTools.toHexString(b);
		System.out.println(hex);;
	}

}
