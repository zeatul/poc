package com.hawk.ecom.picture.web.spring.config;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hawk.ecom.picture.web.CommonExceptionResolver;
import com.hawk.ecom.picture.spring.config.EcomPictureWebConfig;
import com.hawk.ecom.picture.web.AccessInterceptor;

@Configuration
@EnableWebMvc
@Import({ EcomPictureWebConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
//		resolver.setOrder(10);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	@Bean
	public CommonExceptionResolver commonExceptionResolver() {
		return new CommonExceptionResolver();
	}

	/**
	 * 静态资源不处理
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//		// 可以直接使用addResourceLocations 指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
//		registry.addResourceHandler("/myimgs/**").addResourceLocations("file:H:/myimgs/");
//	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(df);
		converter.setObjectMapper(objectMapper);

		// List<MediaType> mediaTypes = new
		// ArrayList(converter.getSupportedMediaTypes());
		// converter.setSupportedMediaTypes(mediaTypes);
		// mediaTypes.addAll(asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML,
		// MediaType.TEXT_XML));
		converters.add(converter);
	}

	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public AccessInterceptor AccessInterceptor() {
		return new AccessInterceptor();
	}

	@Autowired
	private AccessInterceptor accessInterceptor;

	/**
	 * 拦截http请求
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accessInterceptor);
	}

}
