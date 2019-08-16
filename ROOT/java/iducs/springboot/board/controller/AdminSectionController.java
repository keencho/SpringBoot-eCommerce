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
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.DivisionService;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/admin/section")
public class AdminSectionController {
	@Autowired CategoryService categoryService;
	@Autowired DivisionService divisionService;
	@Autowired SectionService sectionService;
	
	@GetMapping("")
	public String sectionHome(Model  model) {
		List<Section> section=sectionService.getSection();
		model.addAttribute("section", section);
		return "admin/section/main";
	}
	
	@GetMapping("/add")
	public String divisionAddForm() {
		return "admin/section/add";
	}

	@PostMapping("/add")
	public String divisionAdd(Long category_no, Long division_no, String name, Model model) {
		Category category = categoryService.getCategoryByNo(category_no);
		Division division = divisionService.getDivisionByNo(division_no);

		Section section = new Section(category, division, name);
		sectionService.saveSection(section);

		return "redirect:/admin/section";
	}
	@GetMapping("/findDivision")
	public String divisionFindDivision(Model model) {
		List<Division> division = divisionService.getDivision();
		model.addAttribute("division", division);
		return "admin/section/findDivision";
	}
	
	@GetMapping("/update/{no}")
	public String sectionUpdateForm(@PathVariable(value = "no") Long no, Model model) {
		Section section= sectionService.getSectionByNo(no);
		model.addAttribute("section", section);
		return "admin/section/update";
	}
	
	
	@PutMapping("/update/{no}")
	public String sectionUpdate(@PathVariable(value = "no") Long no, Long category_no, Long division_no, @Valid Section updateSection, Model model) {
		Category category = categoryService.getCategoryByNo(category_no);
		Division division = divisionService.getDivisionByNo(division_no);
		Section section = sectionService.getSectionByNo(no);
		
		section.setCategory(category);
		section.setDivision(division);
		section.setName(updateSection.getName());
		
		sectionService.updateSection(section);
		return "redirect:/admin/section";
	}
	
	@GetMapping("/del/{no}")
	public String sectionDelete(@PathVariable(value = "no") Long no, Model model) {
		Section section= sectionService.getSectionByNo(no);
		sectionService.deleteSection(section);
		return "redirect:/admin/section";
	}
}
