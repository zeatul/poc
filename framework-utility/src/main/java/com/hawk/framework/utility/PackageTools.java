package com.hawk.framework.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import static com.hawk.framework.utility.ClassPathTools.*;

public class PackageTools {

	private static final String PROTOCOL_FILE = "file";
	private static final String PROTOCOL_JAR = "jar";
	private static final String PREFIX_FILE = "file:";
	private static final String JAR_URL_SEPERATOR = "!/";
	private static final String CLASS_FILE_SUFFIX = ".class";

	public static interface ClassFilter {
		/**
		 * 
		 * @param fileName
		 * @return true:则表示该file符合条件
		 */
		public boolean accept(String className);
	}

	public static interface FileFilter {
		/**
		 * 
		 * @param fileName
		 * @return true:则表示该file符合条件
		 */
		public boolean accept(String fileName);
	}

	public static class XmlFileFilter implements FileFilter {
		public boolean accept(String fileName) {
			if (fileName.toLowerCase().endsWith(".xml"))
				return true;
			return false;
		}

	}

	/**
	 * 返回指定package下的符合条件的文件的带classpath的全路径名
	 * 
	 * @param packageName
	 * @param recursive
	 * @param filter
	 * @return
	 */
	public static List<String> listFile(final boolean recursive, final FileFilter filter ,String... packageNames)  {
		Set<String> fileNameSet = new HashSet<String>();
		for (String packageName : packageNames ){
			List<String> fileNameList = listFile(packageName,filter,recursive);
			for (String fileName : fileNameList){
				fileNameSet.add(fileName);
			}
		}

		return Arrays.asList(fileNameSet.toArray(new String[]{}));
	}
	
	private static List<String> listFile(final String packageName , final FileFilter filter,final boolean recursive){
		String packagePath = dotToClassPath(packageName);
		Enumeration<URL> urls;
		try {
			urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		List<String> fileNameList = new ArrayList<String>();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			String protocol = url.getProtocol();
			if (PROTOCOL_FILE.equals(protocol)) {
				File rootDir = new File(url.getFile());
				findFileInDirectory(fileNameList, rootDir, packageName, recursive, filter);
			} else if (PROTOCOL_JAR.equals(protocol)) {
				findFileInJar(fileNameList, getJarFile(url), packageName, filter);
			} else {
				System.out.println("Unsupport protocol =" + protocol);
			}
		}

		return fileNameList;
	}

	/**
	 * 扫描并列出指定package下的所有符合条件的类的全名称
	 * 
	 * @param packageName
	 * @param recursive
	 *            是否递归取sub package
	 * @param classFilter
	 *            类过滤器
	 * @return
	 * @throws Exception
	 */
	public static List<String> listClass(final String packageName, final boolean recursive, final ClassFilter classFilter) throws Exception {
		String packagePath = dotToClassPath(packageName);
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
		List<String> classNameList = new ArrayList<String>();
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			String protocol = url.getProtocol();
			if (PROTOCOL_FILE.equals(protocol)) {
				File rootDir = new File(url.getFile());
				findClassInDirectory(classNameList, rootDir, packageName, recursive, classFilter);
			} else if (PROTOCOL_JAR.equals(protocol)) {
				findClassInJar(classNameList, getJarFile(url), packageName, classFilter);
			} else {
				System.out.println("Unsupport protocol =" + protocol);
			}
		}

		return classNameList;
	}

	private static void findFileInDirectory(final List<String> fileNameList, final File rootDir, final String packageName, final boolean recursive,
			final FileFilter fileFilter) {

		if (rootDir == null || !rootDir.exists() || !rootDir.isDirectory())
			throw new RuntimeException("Illegal rootDir = " + rootDir);

		File[] files = rootDir.listFiles();

		if (files == null || files.length == 0)
			return;

		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getPath();
				if (fileFilter == null || fileFilter.accept(fileName)) {
					int beginIndex = rootDir.getPath().length() - packageName.length();
					fileName = fileName.substring(beginIndex);
					fileNameList.add(classPathtoAbsoluteClassPath(fileName));
				}

			} else {
				if (recursive) {
					findFileInDirectory(fileNameList, file, packageName + "." + file.getName(), recursive, fileFilter);
				}
			}
		}
	}

	private static void findClassInJar(final List<String> classNameList, File file, String packageName, final ClassFilter classFilter) throws Exception {
		JarFile jarFile = null;
		String packagePath = dotToClassPath(packageName) + "/";

		try {
			jarFile = new JarFile(file);

			Enumeration<JarEntry> en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry je = en.nextElement();
				String name = je.getName();
				if (name.startsWith(packagePath) && name.endsWith(CLASS_FILE_SUFFIX)) {
					String className = name.substring(0, name.length() - CLASS_FILE_SUFFIX.length());
					if (classFilter == null || classFilter.accept(className)) {
						classNameList.add(classPathToDot(className));
					}
				}
			}
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (Exception e) {

				}
			}
		}
	}

	private static void findFileInJar(final List<String> fileNameList, File file, String packageName, final FileFilter fileFilter)  {
		JarFile jarFile = null;
		String packagePath = dotToClassPath(packageName) + "/";

		try {
			try {
				jarFile = new JarFile(file);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			Enumeration<JarEntry> en = jarFile.entries();
			while (en.hasMoreElements()) {
				JarEntry je = en.nextElement();
				String name = je.getName();
				if (name.startsWith(packagePath)) {

					if (fileFilter == null || fileFilter.accept(name)) {
						fileNameList.add(classPathtoAbsoluteClassPath(name));
					}
				}
			}
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (Exception e) {

				}
			}
		}
	}

	private static File getJarFile(URL url) {
		String file = url.getFile();
		if (file.startsWith(PREFIX_FILE))
			file = file.substring(PREFIX_FILE.length());
		int end = file.indexOf(JAR_URL_SEPERATOR);
		if (end != (-1))
			file = file.substring(0, end);
		return new File(file);
	}

	private static void findClassInDirectory(final List<String> classNameList, final File rootDir, final String packageName, final boolean recursive,
			final ClassFilter classFilter) {

		if (rootDir == null || !rootDir.exists() || !rootDir.isDirectory())
			throw new RuntimeException("Illegal rootDir = " + rootDir);

		File[] files = rootDir.listFiles();

		if (files == null || files.length == 0)
			return;

		for (File file : files) {
			if (file.isFile()) {
				String classFileName = file.getPath();
				if (classFileName.endsWith(CLASS_FILE_SUFFIX)) {
					int beginIndex = rootDir.getPath().length() - packageName.length();
					int endIndex = classFileName.length() - CLASS_FILE_SUFFIX.length();
					String className = classPathToDot(classFileName.substring(beginIndex, endIndex));
					if (classFilter == null || classFilter.accept(className)) {
						classNameList.add(className);
					}
				}
			} else {
				if (recursive) {
					findClassInDirectory(classNameList, file, packageName + "." + file.getName(), recursive, classFilter);
				}
			}
		}
	}

	

	// public static void main(String[] args) throws Exception {
	// List<String> classNameList = packageTools.listClass("com.hawk", true, new
	// ClassFilter() {
	// @Override
	// public boolean accept(String className) {
	// return true;
	// }
	// });
	// for (String className : classNameList) {
	// System.out.println(className);
	// }
	// }
}
