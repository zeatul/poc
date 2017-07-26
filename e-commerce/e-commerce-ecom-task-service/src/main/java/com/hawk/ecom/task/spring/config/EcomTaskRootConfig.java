package com.hawk.ecom.task.spring.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.task.spring.config.EcomTaskRootConfig.WebPackage;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.task" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class EcomTaskRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.task\\.controller"));
		}
	}
	
	
	@Bean
	public TaskPool taskPool(){
		int coreSize = 5;
		int maxSize = 100;
		int keepAliveSize = 3;
		int capacity = 6000;
		BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(capacity);
		TaskPool taskPool = new TaskPool(coreSize, maxSize, keepAliveSize, queue);
		return taskPool;
	}
}
