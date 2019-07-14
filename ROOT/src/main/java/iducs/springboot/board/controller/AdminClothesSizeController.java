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

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.service.ClothesSizeService;

@Controller
@RequestMapping("/admin/size")
public class AdminClothesSizeController {
	@Autowired ClothesSizeService clothessizeService;
	
	@GetMapping("")
	public String clothesSizeHome(Model  model) {
		List<ClothesSize> clothessize= clothessizeService.getClothesSize();
		model.addAttribute("clothessize", clothessize);
		return "admin/size/main";
	}
	
	@GetMapping("/add")
	public String clothesSizeAddForm() {
		return "admin/size/add";
	}
	
	@PostMapping("/add")
	public String clothesSizeAdd(String name, Model model) {
		ClothesSize clothessize = new ClothesSize(name);
		clothessizeService.saveClothesSize(clothessize);
		return "redirect:/admin/size";
	}
	
	@GetMapping("/update/{no}")
	public String clothesSizeUpdateForm(@PathVariable(value = "no") Long no, Model model) {
		ClothesSize clothessize = clothessizeService.getClothesSizeByNo(no);
		model.addAttribute("clothessize", clothessize);
		return "admin/size/update";
	}
	
	@PutMapping("/update/{no}")
	public String clothesSizeUpdate(@PathVariable(value = "no") Long no, @Valid ClothesSize updateClothesSize, Model model) {
		ClothesSize clothessize= clothessizeService.getClothesSizeByNo(no);
		clothessize.setName(updateClothesSize.getName());
		clothessizeService.updateClothesSize(clothessize);
		return "redirect:/admin/size";
	}
	
	@GetMapping("/del/{no}")
	public String clothesSizeDelete(@PathVariable(value = "no") Long no, Model model) {
		ClothesSize clothessize = clothessizeService.getClothesSizeByNo(no);
		clothessizeService.deleteClothesSize(clothessize);;
		return "redirect:/admin/size";
	}
}
