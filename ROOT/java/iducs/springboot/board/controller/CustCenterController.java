package iducs.springboot.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/custcenter")
public class CustCenterController {	
	@GetMapping("/faq")
	public String custcenterFaq(
			Model model, 
			HttpSession session) {
		return "home/custcenter/faq";
	}
	
	@GetMapping("/notice")
	public String custcenterNoticeBoard(
			Model model, 
			HttpSession session) {
		return "home/custcenter/notice";
	}
}
