package iducs.springboot.board.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ColorService;
import iducs.springboot.board.service.DivisionService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductSizeService;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired ClothesSizeService clothessizeService;
	@Autowired ColorService colorService;
	@Autowired ProductService productService;
	@Autowired CategoryService categoryService;
	@Autowired DivisionService divisionService;
	@Autowired SectionService sectionService;
	@Autowired ProductSizeService productsizeService;
	
	@GetMapping("/list/category/{cno}")
	public String productList(@PathVariable("cno")long categoryno, @PageableDefault(size=6, sort="no", direction = Sort.Direction.ASC) Pageable pageable, Model model, HttpServletRequest request) throws Exception{
		Category category = categoryService.getCategoryByNo(categoryno);
		
		List <Product> product = productService.getProductByCategoryNo(categoryno, pageable);
		List <ProductSize> productsize = productsizeService.findAll();
		List <Division> division = divisionService.getDivision();
		List <Section> section = sectionService.getSection();
		Page<ProductEntity> page = productService.getProductByCategoryNoPage(categoryno, pageable);
		
		System.out.println(productsize.size());
		
		model.addAttribute("categoryname", category);
		model.addAttribute("productsize", productsize);
		model.addAttribute("division", division);
		model.addAttribute("section", section);
		model.addAttribute("product", product);
		model.addAttribute("page", page);

		return "/home/product/list";
	}
	
}
