package iducs.springboot.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.DivisionService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
	@Autowired ClothesSizeService clothessizeService;
	@Autowired ProductService productService;
	@Autowired CategoryService categoryService;
	@Autowired DivisionService divisionService;
	@Autowired SectionService sectionService;
	
	@GetMapping("")
	public String productHome(Model model) {
		List <Product> product = productService.getProduct();
		model.addAttribute("product", product);
		return "admin/product/main";
	}
	
	@GetMapping("/add")
	public String productAddForm() {
		return "admin/product/add";
	}
	
	@GetMapping("/findCategory")
	public String productFindCategory(Model model) {
		List<Section> section= sectionService.getSection();
		model.addAttribute("section", section);
		return "admin/product/findCategory";
	}
	
	@PostMapping(value="/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String productAdd(Long category_no, Long section_no, Long division_no, String name, String modelname,
			String price, String discount, String listprice, String size, String color, String material, String madeby,
			String madein, String caution, String date, MultipartFile pic1, MultipartFile pic2, MultipartFile pic3,
			MultipartFile pic4,MultipartFile pic5,MultipartFile explainpic,
			String regdate, Model model, MultipartHttpServletRequest request){

		Category category = categoryService.getCategoryByNo(category_no);
		Division division = divisionService.getDivisionByNo(division_no);
		Section section = sectionService.getSectionByNo(section_no);



		
		Product product = new Product(category, division, section, name, modelname, price, discount, listprice, size, color, material, madeby, madein, caution, date, pic1, pic2, pic3, pic4, pic5, explainpic, regdate);
		productService.saveProduct(product);

		return "redirect:/admin/product";
	}

}
