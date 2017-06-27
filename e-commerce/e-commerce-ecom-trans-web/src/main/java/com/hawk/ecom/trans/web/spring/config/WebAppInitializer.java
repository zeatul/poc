package com.hawk.ecom.trans.web.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
//	
//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] { new MyFilter() };
//	}
//	
//	public static class MyFilter implements Filter{
//
//		@Override
//		public void init(FilterConfig filterConfig) throws ServletException {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void destroy() {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}

}
