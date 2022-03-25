package com.javatechie.awselasticbeanstalkexample.config;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//@EnableWebMvc
//@EnableAutoConfiguration
//@PropertySource("classpath:application.properties")
public class MvcConfig implements WebMvcConfigurer
{
	
	//@Autowired
	//private Environment env;
	
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
	}
	

	
//	    @Bean
//	    public ViewResolver getViewResolver() {
//	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	        resolver.setPrefix("templates/");
//	        return resolver;
//	    }
//	
	/*
	 * @Override public void
	 * configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	 * configurer.enable(); }
	 * 
	 * @Bean WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
	 * enableDefaultServlet() { return (factory) ->
	 * factory.setRegisterDefaultServlet(true); }
	 */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		Path adUploadDir      = Paths.get("./images/ad/");
//		Path companyUploadDir = Paths.get("./images/company/");
//		Path storeUploadDir   = Paths.get("./images/store/");
//        Path userPath         = Paths.get("./images/user");
//        Path productPath      = Paths.get("./images/product/");
//        Path catalogPath      = Paths.get("./images/catalog");
//		String adUploadPath = adUploadDir.toFile().getAbsolutePath();
//		String companyUploadPath = companyUploadDir.toFile().getAbsolutePath();
//		String storeUploadPath = storeUploadDir.toFile().getAbsolutePath();
//
//		registry.addResourceHandler("/images/ad/**").addResourceLocations("file:/" +adUploadPath + "/");
//		registry.addResourceHandler("/images/company/**").addResourceLocations("file:/" +companyUploadPath + "/");
//		registry.addResourceHandler("/images/store/**").addResourceLocations("file:/" +storeUploadPath + "/");
//		registry.addResourceHandler("/images/user/**").addResourceLocations("file:/" +userPath + "/");
//		registry.addResourceHandler("/images/product/**").addResourceLocations("file:/" +productPath + "/");
//		registry.addResourceHandler("/images/catalog/**").addResourceLocations("file:/" +catalogPath + "/");

	}

	
	
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}
}
