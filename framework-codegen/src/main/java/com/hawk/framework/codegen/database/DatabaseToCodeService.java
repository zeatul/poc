package com.hawk.framework.codegen.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hawk.framework.codegen.database.config.DatabaseConfigure;
import com.hawk.framework.codegen.database.config.EnumDialect;
import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.codegen.database.config.IProjectConfigure;
import com.hawk.framework.codegen.database.config.ProjectConfigure;
import com.hawk.framework.codegen.database.convert.DomainConverterFactory;
import com.hawk.framework.codegen.database.convert.IDomainConverter;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Domain;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.codegen.utils.ProjectTools;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 解析数据库，生成对应的domain，sqlmap,mapper
 * @author pzhang1
 *
 */
public class DatabaseToCodeService {

	private  Configuration cfg = new Configuration();
	
	public DatabaseToCodeService(){
		cfg.setClassForTemplateLoading(DatabaseToCodeService.class, "");
	}
	
	/**
	 * 分析数据库,生成对应的代码(domain,mapper,sql)
	 * @param configFileClassPath 配置文件所在的classPath,例如com.hawk.ecom.codegen.user
	 * @throws Throwable
	 */
	public void execute(String configFileClassPath) throws Throwable{
		IDatabaseConfigure databaseConfigure = DatabaseConfigure.build(configFileClassPath);
		IProjectConfigure projectConfigure = ProjectConfigure.build(configFileClassPath);

		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);

		Date stdt = new Date();
		Database database = dbParser.parse();
		Date endt = new Date();
		System.out.println(
				"Success parse database , find " + database.getTableList().size() + " tables, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		IDomainConverter domianConverter = DomainConverterFactory.build(databaseConfigure.getDialect());

		String packageName = projectConfigure.getRootPackage() + "." + projectConfigure.getSubPackage()+ ".persist";
		List<Domain> domainList = new ArrayList<Domain>();
		stdt = new Date();
		for (Table table : database.getTableList()) {
			Domain domain = domianConverter.convert(table);
			domain.setPackageName(packageName);
			domainList.add(domain);
		}
		endt = new Date();
		System.out.println("Convert table tod domain cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		stdt = new Date();
		writeDomain(domainList, projectConfigure);
		endt = new Date();
		System.out.println("Success to generate domain.java file, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		stdt = new Date();
		writeMapper(domainList, projectConfigure);
		endt = new Date();
		System.out.println("Success to generate mapper.java file, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		stdt = new Date();
		writeSqlMapper(domainList, projectConfigure, databaseConfigure.getDialect());
		endt = new Date();
		System.out.println("Success to generate sql mapper.xml file, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

	}

	/**
	 * 生成domain.java文件
	 * 
	 * @param domainList
	 * @param projectConfigure
	 * @throws IOException
	 * @throws Exception
	 */
	private void writeDomain(List<Domain> domainList, IProjectConfigure projectConfigure) throws IOException, Exception {

		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/domain.ftl");

		String directory = ProjectTools.computeProjectSourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "persist.domain");
		ProjectTools.clearDirectory(directory, "Domain.java");

		for (Domain domain : domainList) {

			String filePath = directory + File.separator + domain.getClassName() + "Domain.java";
			if (new File(filePath).exists())
				throw new RuntimeException("Exists file = " + filePath);

			FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			template.process(domain, out);
			out.flush();
			out.close();
		}

	}

	/**
	 * 生成mapper.java文件
	 * 
	 * @param domain
	 * @throws Exception
	 */
	private void writeMapper(List<Domain> domainList, IProjectConfigure projectConfigure) throws Exception {
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/mapper.ftl");

		String directory = ProjectTools.computeProjectSourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "persist.mapper");
		ProjectTools.clearDirectory(directory, "Mapper.java");

		for (Domain domain : domainList) {

			String filePath = directory + File.separator + domain.getClassName() + "Mapper.java";
			if (new File(filePath).exists())
				throw new RuntimeException("Exists file = " + filePath);

			FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			template.process(domain, out);
			out.flush();
			out.close();
		}
	}

	/**
	 * 生成mapper.xml文件
	 * 
	 * @param domain
	 * @throws Exception
	 */
	private void writeSqlMapper(List<Domain> domainList, IProjectConfigure projectConfigure, EnumDialect dialect) throws Exception {
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/mybatis_" + dialect.getValue() + ".ftl");
		String directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "persist.mapper");
		ProjectTools.clearDirectory(directory, "Mapper.xml");

		for (Domain domain : domainList) {

			String filePath = directory + File.separator + domain.getClassName() + "Mapper.xml";
			if (new File(filePath).exists())
				throw new RuntimeException("Exists file = " + filePath);

			FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			template.process(domain, out);
			out.flush();
			out.close();
		}
	}

}
