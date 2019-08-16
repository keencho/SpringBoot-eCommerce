package iducs.springboot.board.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import iducs.springboot.board.service.SectionService;

@Configuration
public class HeaderControllConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public HeaderControll headerControll() {
		return new HeaderControll();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(headerControll()).addPathPatterns("/**");
	}
}
