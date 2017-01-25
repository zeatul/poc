//package com.hawk.codegen;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//
//
//
//
//import com.hawk.codegen.config.IProjectConfigure;
//import com.hawk.codegen.meta.Table;
//import com.hawk.codegen.service.DatabaseParser;
//import com.hawk.codegen.service.DomainHelper;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//
//public class AppWeixin {
//	
//	
//	private static class Config implements IProjectConfigure{
//		
//		/**
//		 * database
//		 */
//		public static String DRIVER = "org.gjt.mm.mysql.Driver";
//		public static String URL = "jdbc:mysql://127.0.0.1:3306/weixin-cinema?useUnicode=true&characterEncoding=utf-8";
//		public static String USER = "root";
//		public static String PASSWORD = "123456";
//		public static String SCHEMA = "weixin-cinema";
//		//		public static String FILTER = "T_CINEMA%";
//									public static String FILTER = "T_SYSTEM%";
//		public static String DIALECT = "mysql";
//		
//		/**
//		 * 
//		 */
//		public static String ROOT_PACKAGE = "com.hante.weixin";
//		//	public final static String ROOT_DIRECTORY = "C://mydata//workspace//workspace_weixin//cinema-server//weixin-cinema-service//";
//					public final static String ROOT_DIRECTORY = "C://mydata//workspace//workspace_weixin//cinema-server//weixin-system-service//";
//		public final static String CODE_DIRECTORY = "src//main//java//";
//		public final static String RESOURCE_DIRECTORY = "src//main//resources//";
//		public final static String ROOT_PACKAGE_DIRECTORY = "com//hante//weixin//";
//		
//		public String getDriver() {
//			return DRIVER;
//		}
//		public String getUrl() {
//			return URL;
//		}
//		public String getUser() {
//			return USER;
//		}
//		public String getPassword() {
//			return PASSWORD;
//		}
//		public String getSchema() {
//			return SCHEMA;
//		}
//		public String getFilter() {
//			return FILTER;
//		}
//		public String getDialect() {
//			return DIALECT;
//		}
//		public String getRootPackage() {
//			return ROOT_PACKAGE;
//		}
//
//	}
//
//	
//	private static Logger logger = LoggerFactory.getLogger(DatabaseParser.class); 
//	
//	private static DomainHelper domainHelper =  new DomainHelper();
//	private static Configuration cfg = new Configuration();
//	static{		
//		cfg.setClassForTemplateLoading(AppWeixin.class, "");
//	}
//
//	public static void main(String[] args) {
//		
//		try {
//			DatabaseParser parser = new DatabaseParser(new Config());
//			
//			List<Table> tableList = parser.parseDatabase();
//			
//			for (Table table : tableList){
//				writeDomain(table);
//				writeMapper(table);
//				writeMybatisr(table);
//			}
//
//			
//		} catch (Exception e) {
//			logger.error("parse error",e);
//		}
//	}
//	
//	private final static String rootDirectory = Config.ROOT_DIRECTORY;
//	private final static String rootPackageDirectory = Config.ROOT_PACKAGE_DIRECTORY;
//	private final static String codeDirectory = Config.CODE_DIRECTORY;
//	private final static String resourceDirectory = Config.RESOURCE_DIRECTORY;
//	
//	private static void writeDomain(Table table) throws IOException, TemplateException{
//		
//		/* 获取或创建模板*/
//		Template template = cfg.getTemplate("templates/domain.ftl");
//		/* 创建数据模型 */
//		Map<String,Object> root = domainHelper.transfer(table);
//		/* 将模板和数据模型合并 */
//		String className = root.get("className").toString();
//		String packageName = root.get("moduleName").toString();
//		
//		String filePath = "";
//		String directory ="";
////		if (packageName.equals("userinfo")){
////			directory= "C://my data//develop//workspace//yiyundong//esports//project//esports-userinfo-service//src//main//java//com//baoflag//esports//userinfo//domain";
////			
////		}else if (packageName.equals("system")){
////			directory= "C://my data//develop//workspace//yiyundong//esports//project//esports-system-service//src//main//java//com//baoflag//esports//system//domain";
////		}
//		
//		directory = rootDirectory + codeDirectory+rootPackageDirectory + packageName + "//domain";
//		
//		filePath = directory+"/"+className+".java";
//		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
//		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
//		template.process(root, out);
//		out.flush();
//		out.close();
//		
//	}
//	
//	private static  void clearDirectory(String directory){
//		File file = new File(directory);
//		if (file.isDirectory()){
//			String[] f = file.list();
//			for (String str : f){
//				File f1 = new File(file + File.separator +str);
//				f1.delete();
//			}
//		}
//		
//	}
//	
//	private static void writeMapper(Table table) throws TemplateException, IOException{
//		/* 获取或创建模板*/
//		Template template = cfg.getTemplate("templates/mapper.ftl");
//		/* 创建数据模型 */
//		Map<String,Object> root = domainHelper.transfer(table);
//		/* 将模板和数据模型合并 */
//		String className = root.get("className").toString();
//		String packageName = root.get("moduleName").toString();
//		
//		String filePath = "";
//		String directory ="";
//
//		directory = rootDirectory + codeDirectory+rootPackageDirectory + packageName + "//mapper";
//		
//		filePath = directory+"/"+className+"Mapper.java";
//		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
//		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
//		template.process(root, out);
//		out.flush();
//		out.close();
//	}
//	
//	private static void writeMybatisr(Table table) throws TemplateException, IOException{
//		/* 获取或创建模板*/
//		Template template = cfg.getTemplate("templates/mybatis_"+Config.DIALECT+".ftl");
//		/* 创建数据模型 */
//		Map<String,Object> root = domainHelper.transfer(table);
//		/* 将模板和数据模型合并 */
//		String className = root.get("className").toString();
//		String packageName = root.get("moduleName").toString();
//		
//		String filePath = "";
//		String directory = "";
//
//		
//		directory = rootDirectory + resourceDirectory+rootPackageDirectory + packageName + "//mapper";
//		
//		filePath = directory+"/"+className+"Mapper.xml";
//		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
//		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
//		template.process(root, out);
//		out.flush();
//		out.close();
//	}
//
//}
