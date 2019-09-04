package iducs.springboot.board.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AdminHomeExceptionConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AdminHomeException()).addPathPatterns("/admin/**")
				.excludePathPatterns("/admin/login");
	}
}
