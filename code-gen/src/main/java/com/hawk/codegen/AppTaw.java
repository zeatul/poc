package com.hawk.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.codegen.config.Configure;
import com.hawk.codegen.database.config.EnumDialect;
import com.hawk.codegen.meta.Table;
import com.hawk.codegen.service.DatabaseParser;
import com.hawk.codegen.service.DomainHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AppTaw {

	private static Logger logger = LoggerFactory.getLogger(DatabaseParser.class);
	
	private final static String DOMAIN = "domain";
	private final static String MAPPER = "mapper";

	private static DomainHelper domainHelper = new DomainHelper();
	private static Configuration cfg = new Configuration();
	static {
		cfg.setClassForTemplateLoading(AppTaw.class, "");
	}

	public static void main(String[] args) {
//		generateUm();
//		generateSM();
		generateTM();
//		generatePM();
	}
	
	/**
	 * 生成图片管理类
	 */
	private static void generatePM(){
		Configure configure = buildConfigure("pm");
		configure.setProjectName("taw-picture-service");
		configure.setRootPackage("com.taw");
		configure.setSubPackage("picture");
		
		generate(configure);
	}
	
	/**
	 * 生成场景管理类
	 */
	private static void generateTM(){
		Configure configure = buildConfigure("tm");
		configure.setProjectName("taw-scene-service");
		configure.setRootPackage("com.taw");
		configure.setSubPackage("scene");
		
		generate(configure);
	}
	
	/**
	 * 生成短信管理类
	 */
	private static void generateSM(){
		Configure configure = buildConfigure("sm");
		configure.setProjectName("taw-public");
		configure.setRootPackage("com.hawk.pub");
		configure.setSubPackage("sms");
		
		generate(configure);
	}
	
	

	/**
	 * 生成用户管理类
	 */
	private static void generateUm() {
		Configure configure = buildConfigure("um");
		configure.setProjectName("taw-user-service");
		configure.setRootPackage("com.taw");
		configure.setSubPackage("user");
		
		generate(configure);
		
		
	}
	
	private static void generate(Configure configure){
		try {
			DatabaseParser parser = new DatabaseParser(configure);
			
			List<Table> tableList = parser.parseDatabase();
			
			/*清理目录*/
			String dir = configure.computeEntireSourceCodeDir() + File.separator+ DOMAIN;
			configure.clearDirectory(dir);
			dir = configure.computeEntireSourceCodeDir() + File.separator+ MAPPER;
			configure.clearDirectory(dir);
			dir = configure.computeEntireResourceDir() + File.separator+ MAPPER;
			configure.clearDirectory(dir);
			
			for (Table table : tableList){
				writeDomain(table,configure);
				writeMapper(table,configure);
				writeMybatisr(table,configure);
			}

			
		} catch (Exception e) {
			logger.error("parse error",e);
		}
	}
	
	
	
	private static void writeDomain(Table table,Configure configure) throws IOException, TemplateException{
		
		
		
		/* 获取或创建模板*/
		Template template = cfg.getTemplate("templates/domain.ftl");
		/* 创建数据模型 */
		Map<String,Object> root = domainHelper.transfer(table);
		
		/*设置子模块名和完整的package名*/
		root.put("moduleName", configure.getSubPackage());
		root.put("packageName",configure.getRootPackage() + "."+configure.getSubPackage());
		/* 将模板和数据模型合并 */
		String className = root.get("className").toString();	
		
		String directory = configure.computeEntireSourceCodeDir() + File.separator+ DOMAIN;		
		String filePath = directory+ File.separator+className+"Domain.java";
		
		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
		template.process(root, out);
		out.flush();
		out.close();
		
	}
	
	
	private static void writeMapper(Table table,Configure configure) throws TemplateException, IOException{
		/* 获取或创建模板*/
		Template template = cfg.getTemplate("templates/mapper.ftl");
		/* 创建数据模型 */
		Map<String,Object> root = domainHelper.transfer(table);
		/*设置子模块名和完整的package名*/
		root.put("moduleName", configure.getSubPackage());
		root.put("packageName",configure.getRootPackage() + "."+configure.getSubPackage());
		/* 将模板和数据模型合并 */
		String className = root.get("className").toString();
		
		String directory = configure.computeEntireSourceCodeDir() + File.separator+ MAPPER;		
		String filePath = directory+ File.separator+className+"Mapper.java";
		
		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
		template.process(root, out);
		out.flush();
		out.close();
	}
	
	private static void writeMybatisr(Table table,Configure configure) throws TemplateException, IOException{
		/* 获取或创建模板*/
		Template template = cfg.getTemplate("templates/mybatis_"+configure.getDialect()+".ftl");
		/* 创建数据模型 */
		Map<String,Object> root = domainHelper.transfer(table);
		/*设置子模块名和完整的package名*/
		root.put("moduleName", configure.getSubPackage());
		root.put("packageName",configure.getRootPackage() + "."+configure.getSubPackage());
		/* 将模板和数据模型合并 */
		String className = root.get("className").toString();

		String directory = configure.computeEntireResourceDir() + File.separator+ MAPPER;		
		String filePath = directory+ File.separator+className+"Mapper.xml";
		
		FileOutputStream fileOutputStream = new FileOutputStream(filePath,false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8"); 
		template.process(root, out);
		out.flush();
		out.close();
	}
	
	public static Configure buildConfigure(String dbname){
		Configure configure = new Configure();
		configure.setDriver("org.gjt.mm.mysql.Driver");
		
		String url = "jdbc:mysql://127.0.0.1:3306/" +dbname+ "?useUnicode=true&characterEncoding=utf-8";
		configure.setUrl(url);

		configure.setUser("root");
		configure.setPassword("password");
		
		configure.setSchema(dbname);
		String filter = "t_"+dbname+"%";
		configure.setFilter(filter);
		
		configure.setDialect(EnumDialect.Mysql);
		
		return configure;
	
	}



}
