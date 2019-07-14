package iducs.springboot.board.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController{
		
	@GetMapping("")
	public String adminHome() {
		return "admin/main/index";
	}
	@GetMapping("/404")
	public String adminHomeError(HttpSession session) {
		return "admin/main/404";
	}
	
	
}
