package iducs.springboot.board.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.QuestionService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.utils.HttpSessionUtils;

@Controller
public class HomeController {
	
	@Autowired 
	private UserService userService; // 의존성 주입(Dependency Injection) : 
	
	@Autowired 
	private QuestionService questionService;
	
//	@GetMapping("/initdb") 
//	public String initialize() {
//		for(int i = 1;i <= 10; i++)
//			userService.saveUser(new User("a" + i, "p" + i, "name" + i, "contact" + i));	
//		for(int i = 1;i <= 5; i++) {
//			for(int j = 1; j <= 2; j++)
//			questionService.saveQuestion(new Question("제목 " + j, userService.getUserByUserId("u"+i) , "내용 " + j));
//		}
//		return "index";
//	}
	@GetMapping("/")
	public String home() {		
		return "main/index";
	}
	@GetMapping("/404")
	public String error() {		
		return "main/404";
	}
	@GetMapping("/questions/form") // 등록폼은 form URL을 가지도록 규칙화하겠음
	public String questionForm(HttpSession session, Model model) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login-form";
		}
		User writer = (User) session.getAttribute("user");
		model.addAttribute("writer", writer);
		return "/questions/register";
	}	
	@GetMapping("/users/login-form")
	public String loginForm(Model model) {
		return "/users/login";
	}
	@GetMapping("/users/form") // 등록폼은 form URL을 가지도록 함, 다른 폼은 이름을 명명하기로 수정함
	public String registerForm() {
		return "/users/register";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
    @GetMapping("/hello")
    public Collection<String> sayHello() {
        return IntStream.range(0, 10)
          .mapToObj(i -> "Hello number " + i)
          .collect(Collectors.toList());
    }
}
