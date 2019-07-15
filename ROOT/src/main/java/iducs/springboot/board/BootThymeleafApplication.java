package iducs.springboot.board;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;


@SpringBootApplication
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
public class BootThymeleafApplication extends SpringBootServletInitializer{
	private final int MAX_SIZE = 10 * 1024 * 1024;
	public static void main(String[] args) {
		SpringApplication.run(BootThymeleafApplication.class, args);
		
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BootThymeleafApplication.class);
	}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
	MultipartConfigFactory factory = new MultipartConfigFactory();
	factory.setMaxFileSize("512MB");
	factory.setMaxRequestSize("512MB");
	return factory.createMultipartConfig();
	}
	
	
	@Bean(name = "multipartResolver")  
    public MultipartResolver multipartResolver()  
    {  
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();  
        return resolver;  
    }
	}
	

