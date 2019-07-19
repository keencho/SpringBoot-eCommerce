package iducs.springboot.board.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import iducs.springboot.board.domain.Section;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.SectionService;
import iducs.springboot.board.utils.HttpSessionUtils;

@Controller
public class HomeController {
	@Autowired SectionService sectionService;
	
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
	@GetMapping("")
	public void header(Model model) {
		List<Section> section=sectionService.getSection();
		model.addAttribute("section", section);
	}
	@GetMapping("/")
	public String home() {
		return "main/index";
	}
	@GetMapping("/404")
	public String error() {		
		return "main/404";
	}

}
