package iducs.springboot.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.DivisionService;
import iducs.springboot.board.service.ProductQuestionService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.SectionService;

@Controller
@RequestMapping("/admin/communication")
public class AdminProductCommunicationController {
	@Autowired ProductService productService;
	@Autowired ProductQuestionService productquestionService;
	@Autowired CategoryService categoryService;
	@Autowired DivisionService divisionService;
	@Autowired SectionService sectionService;
	
	@GetMapping("/category")
	public String productQuestionCategory(Model  model) {
		List<Category> category = categoryService.getCategory();
		model.addAttribute("category", category);
		return "admin/communication/productCategory";
	}
	
	@GetMapping("/division/{categoryno}")
	public String productQuestionDivision(
			@PathVariable(value="categoryno") long categoryno,
			Model  model) {
		List<Division> division= divisionService.getDivisionByCategoryNo(categoryno);
		model.addAttribute("division", division);
		return "admin/communication/productDivision";
	}
	
	@GetMapping("/section/{divisionno}")
	public String productQuestionSection(
			@PathVariable(value="divisionno") long divisionno,
			Model  model) {
		List<Section> section = sectionService.getSectionByDivisionNo(divisionno);
		model.addAttribute("section", section);
		return "admin/communication/productSection";
	}
	
	@GetMapping("/product/list/section/{sectionno}")
	public String productList(
			@PathVariable(value="sectionno") long sectionno,
			Model  model) {
		List<Product> product = productService.getProductBySectionNo(sectionno);
		model.addAttribute("product", product);
		return "admin/communication/productList";
	}
	
	@GetMapping("/product/question/list/{productno}")
	public String productQuestionList(
			@PathVariable(value="productno") long productno,
			Model  model) {
		List<ProductQuestion> productquestion = productquestionService.getProductQuestionOriginal(productno);
		model.addAttribute("question", productquestion);
		return "admin/communication/productQuestionList";
	}
	
	@GetMapping("/product/question/del/{no}")
	public String productQuestionDel(@PathVariable(value = "no") Long no) {
		ProductQuestion productquestion = productquestionService.getProductQuestionByNo(no);
		long sectionno = productquestion.getProduct_no().getSection().getNo();
		productquestionService.deleteProductQuestion(productquestion);
		return "redirect:/admin/communication/product/question/list/" + sectionno;
	}
	
	@GetMapping("/product/question/update/{no}")
	public String productQuestionUpdateForm(@PathVariable(value = "no") Long no, Model model) {
		ProductQuestion productquestion = productquestionService.getProductQuestionByNo(no);
		model.addAttribute("question", productquestion);
		return "admin/communication/productAnswer";
	}
	
	@ResponseBody
	@PostMapping("/product/question/update")
	public String productQuestionUpdate(
			@RequestParam(value = "no") long no,
			@RequestParam(value = "status") int status,
			@RequestParam(value = "answer") String answer,
			Model model) {
		ProductQuestion productquestion = productquestionService.getProductQuestionByNo(no);
		long productno = productquestion.getProduct_no().getNo();
		productquestion.setAnswer(answer);
		productquestion.setStatus(status);

		productquestionService.updateProductQuestion(productquestion);
		return	"/admin/communication/product/question/list/" + productno;
	}
}
