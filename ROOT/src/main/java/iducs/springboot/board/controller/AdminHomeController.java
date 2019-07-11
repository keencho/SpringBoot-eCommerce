package iducs.springboot.board.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminHomeController{
	
	@Autowired
	private UserService userService;
		
	@GetMapping("")
	public String adminHome() {
		return "admin/main/index";
	}
	@GetMapping("/404")
	public String adminHomeError(HttpSession session) {
		return "admin/main/404";
	}
	
	
}
