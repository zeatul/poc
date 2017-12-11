package com.hawk.ecom.picture.web.spring.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.hawk.ecom.picture.config.UploadFileConfigure;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	private final static Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);
	


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
	
	private final static UploadFileConfigure uploadFileConfigure;
	
	static{
		try {
			uploadFileConfigure = new UploadFileConfigure();
			PropertiesConfiguration configuration = new PropertiesConfiguration("/com/hawk/ecom/picture/env/ecom_picture.properties");
			
			String location = configuration.getString("ecom.picture.location");
			logger.info("ecom.picture.location={}",location);
			uploadFileConfigure.setLocation(location);
			

			long maxFilesSize = configuration.getLong("ecom.picture.maxFilesSize");
			logger.info("ecom.picture.maxFilesSize={}",maxFilesSize);
			uploadFileConfigure.setMaxFilesSize(maxFilesSize);
			
			long maxRequestSize = configuration.getLong("ecom.picture.maxRequestSize"); 
			logger.info("ecom.picture.maxRequestSize={}",maxRequestSize);
			uploadFileConfigure.setMaxRequestSize(maxRequestSize);
			
			int fileSizeThreshold = configuration.getInt("ecom.picture.fileSizeThreshold") ;
			logger.info("ecom.picture.fileSizeThreshold={}",fileSizeThreshold);
			uploadFileConfigure.setFileSizeThreshold(fileSizeThreshold);
			
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	

	@Override
	protected void customizeRegistration(Dynamic registration) {
		 
		registration.setMultipartConfig(new MultipartConfigElement(uploadFileConfigure.getLocation(), uploadFileConfigure.getMaxFilesSize()
				, uploadFileConfigure.getMaxRequestSize(), uploadFileConfigure.getFileSizeThreshold()));
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
