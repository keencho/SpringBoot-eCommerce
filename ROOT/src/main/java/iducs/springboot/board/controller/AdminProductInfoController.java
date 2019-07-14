package iducs.springboot.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.ProductInfo;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ProductInfoService;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/admin/productinfo")
public class AdminProductInfoController {
	@Autowired ClothesSizeService clothessizeService;
	@Autowired ProductInfoService productinfoService;
	@Autowired SectionService sectionService;
	
	@GetMapping("")
	public String productInfoHome(Model model) {
		List <ProductInfo> productinfo = productinfoService.getProductInfo();
		model.addAttribute("productinfo", productinfo);
		return "admin/productinfo/main";
	}
	
	@GetMapping("/add")
	public String productInfoAddForm() {
		return "admin/productinfo/add";
	}
	
	@GetMapping("/findCategory")
	public String productInfoFindCategory(Model model) {
		List<Section> section= sectionService.getSection();
		model.addAttribute("section", section);
		return "admin/productinfo/findCategory";
	}

}
