package iducs.springboot.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Section;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	@Autowired SectionService sectionService;

	@GetMapping("/getForm")
	public String checkoutForm(Model model) {
		return "home/checkout/checkoutForm";
	}
}
