package com.hawk.framework.codegen.spring.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.config.DatabaseConfigure;
import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.dic.persist.domain.WordDomain;
import com.hawk.framework.dic.persist.mapper.WordMapper;
import com.hawk.framework.dic.persist.mapperex.WordExMapper;
import com.hawk.framework.dic.service.WordService;
import com.hawk.framework.utility.tools.StringTools;
import com.mchange.v2.c3p0.ComboPooledDataSource;



@Configuration
@MapperScan(basePackageClasses = { WordExMapper.class, WordMapper.class })
@ComponentScan(basePackageClasses={WordService.class})
public class CodeGenConfig {	
	
	@Bean
	public DataSource dataSource() throws Exception {
		IDatabaseConfigure databaseConfigure = DatabaseConfigure.build(DbToDicService.PACKAGE_NAME);
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setDriverClass(databaseConfigure.getDriver());
		dataSource.setJdbcUrl(databaseConfigure.getUrl());
		dataSource.setUser(databaseConfigure.getUser());
		dataSource.setPassword(databaseConfigure.getPassword());
		
		return dataSource;
	}
	

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		String packageName = WordDomain.class.getPackage().getName();
		String str = StringTools.concatWithSymbol(";", packageName, packageName + "ex");
		sqlSessionFactory.setTypeAliasesPackage(str);
		return sqlSessionFactory;
	}
	
	@Configuration
	@EnableTransactionManagement
	public static class TransactionConfig {
		@Bean
		public PlatformTransactionManager transactionManager(DataSource dataSource) {
			DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
			transactionManager.setDataSource(dataSource);
			return transactionManager;
		}
	}

}
