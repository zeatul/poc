package com.hawk.ecom.picture.web.spring.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Autowired
	private Environment evn;

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/", "*.do" };
	}
	
	

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("/usr/local/tomcat/uploads", 2097152, 4194304, 0));
	}
	
	 
	
	
	
	//
	// @Override
	// protected Filter[] getServletFilters() {
	// return new Filter[] { new MyFilter() };
	// }
	//
	// public static class MyFilter implements Filter{
	//
	// @Override
	// public void init(FilterConfig filterConfig) throws ServletException {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void doFilter(ServletRequest request, ServletResponse response,
	// FilterChain chain) throws IOException, ServletException {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void destroy() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// }

}
