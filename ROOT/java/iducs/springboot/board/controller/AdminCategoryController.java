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
import iducs.springboot.board.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {
	@Autowired CategoryService categoryService;
	@GetMapping("")
	public String categoryHome(Model  model) {
		List<Category> category = categoryService.getCategory();
		model.addAttribute("category", category);
		return "admin/category/main";
	}
	
	@GetMapping("/add")
	public String categoryAddForm() {
		return "admin/category/add";
	}
	
	@PostMapping("/add")
	public String categoryAdd(String name, String englishName, Model model) {
		Category category = new Category(name,englishName);
		
		categoryService.saveCategory(category);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/update/{no}")
	public String categoryUpdateForm(@PathVariable(value = "no") Long no, Model model) {
		Category category = categoryService.getCategoryByNo(no);
		model.addAttribute("category", category);
		return "admin/category/update";
	}
	
	@PutMapping("/update/{no}")
	public String categoryUpdate(@PathVariable(value = "no") Long no, @Valid Category updateCategory, Model model) {
		Category category = categoryService.getCategoryByNo(no);
		category.setName(updateCategory.getName());
		category.setEnglishName(updateCategory.getEnglishName());
		categoryService.updateCategory(category);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/del/{no}")
	public String categoryDelete(@PathVariable(value = "no") Long no, Model model) {
		Category category = categoryService.getCategoryByNo(no);
		categoryService.deleteCategory(category);
		return "redirect:/admin/category";
	}

}
