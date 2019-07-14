package iducs.springboot.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Color;
import iducs.springboot.board.service.ColorService;

@Controller
@RequestMapping("/admin/color")
public class AdminColorController {
	@Autowired ColorService colorService;
	
	@GetMapping("")
	public String colorHome(Model  model) {
		List<Color> color= colorService.getColor();
		model.addAttribute("color", color);
		return "admin/color/main";
	}
	
	@GetMapping("/add")
	public String colorAddForm() {
		return "admin/color/add";
	}
	
	@PostMapping("/add")
	public String colorAdd(String name, Model model) {
		Color color = new Color(name);
		colorService.saveColor(color);
		return "redirect:/admin/color";
	}
	
	@GetMapping("/update/{no}")
	public String colorUpdateForm(@PathVariable(value = "no") Long no, Model model) {
		Color color = colorService.getColorByNo(no);
		model.addAttribute("color", color);
		return "admin/color/update";
	}
	
	@PutMapping("/update/{no}")
	public String colorUpdate(@PathVariable(value = "no") Long no, @Valid Color updateColor, Model model) {
		Color color= colorService.getColorByNo(no);
		color.setName(updateColor.getName());
		colorService.updateColor(color);
		return "redirect:/admin/color";
	}
	
	@GetMapping("/del/{no}")
	public String colorDelete(@PathVariable(value = "no") Long no, Model model) {
		Color color = colorService.getColorByNo(no);
		colorService.deleteColor(color);;
		return "redirect:/admin/color";
	}
}
