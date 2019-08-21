package iducs.springboot.board.exception;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UserExceptionConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserException()).addPathPatterns("/user/login").addPathPatterns("/user/register");
		registry.addInterceptor(new UserNullException()).addPathPatterns("/mypage")
														.addPathPatterns("/mypage/**")
														.excludePathPatterns("/mypage/withdraw/complete");
	}
}
