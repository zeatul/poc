package com.hawk.ecom.svp.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SvpWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{SvpRootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{SvpWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
