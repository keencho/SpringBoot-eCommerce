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

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.DivisionService;

@Controller
@RequestMapping("/admin/division")
public class AdminDivisionController {
	@Autowired CategoryService categoryService;
	@Autowired DivisionService divisionService;
	
	@GetMapping("")
	public String divisionHome(Model  model) {
		List<Division> division= divisionService.getDivision();
		model.addAttribute("division", division);
		return "admin/division/main";
	}
	
	@GetMapping("/add")
	public String divisionAddForm() {
		return "admin/division/add";
	}

	@PostMapping("/add")
	public String divisionAdd(Long category_no, String name, Model model) {
		Category category = categoryService.getCategoryByNo(category_no);
		Division division = new Division(category, name);
		divisionService.saveDivision(division);

		return "redirect:/admin/division";
	}
	@GetMapping("/findCategory")
	public String divisionFindCategory(Model model) {
		List<Category> category = categoryService.getCategory();
		model.addAttribute("category", category);
		return "admin/division/findCategory";
	}
	
	@GetMapping("/update/{no}")
	public String divisionUpdateForm(@PathVariable(value = "no") Long no, Model model) {
		Division division = divisionService.getDivisionByNo(no);
		model.addAttribute("division", division);
		return "admin/division/update";
	}
	
	
	@PutMapping("/update/{no}")
	public String divisionUpdate(@PathVariable(value = "no") Long no, Long category_no, @Valid Division updateDivision, Model model) {
		Category category = categoryService.getCategoryByNo(category_no);
		Division division = divisionService.getDivisionByNo(no);
		division.setCategory(category);
		division.setName(updateDivision.getName());
		divisionService.updateDivision(division);
		return "redirect:/admin/division";
	}
	
	@GetMapping("/del/{no}")
	public String divisionDelete(@PathVariable(value = "no") Long no, Model model) {
		Division division= divisionService.getDivisionByNo(no);
		divisionService.deleteDivision(division);
		return "redirect:/admin/division";
	}
}
