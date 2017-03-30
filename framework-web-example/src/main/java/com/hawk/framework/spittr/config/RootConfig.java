package com.hawk.framework.spittr.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.hawk.framework.spittr.config.RootConfig.WebPackage;

@Configuration
@Import(DataConfig.class)
@ComponentScan(basePackages={"com.hawk.framework.spittr"},excludeFilters={@Filter(type=FilterType.CUSTOM,value=WebPackage.class)})
public class RootConfig {
	public static class WebPackage extends RegexPatternTypeFilter{
		public WebPackage(){
			super(Pattern.compile("spittr\\.web"));
		}
	}
}
