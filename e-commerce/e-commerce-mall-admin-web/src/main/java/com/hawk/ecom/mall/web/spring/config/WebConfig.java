package com.hawk.ecom.mall.web.spring.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hawk.ecom.mall.web.CommonExceptionResolver;
import com.hawk.ecom.muser.spring.config.MuserWebConfig;
import com.hawk.ecom.product.spring.config.ProductWebConfig;
import com.hawk.ecom.sms.spring.config.SmsWebConfig;
import com.hawk.ecom.mall.spring.config.MallAdminWebConfig;
import com.hawk.ecom.mall.web.AccessInterceptor;

@Configuration
@EnableWebMvc
@Import({MallAdminWebConfig.class, MuserWebConfig.class,SmsWebConfig.class ,ProductWebConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(10);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
	public CommonExceptionResolver commonExceptionResolver(){
		return new CommonExceptionResolver();
	}

	/**
	 * 静态资源不处理
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

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
	public AccessInterceptor AccessInterceptor(){
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
