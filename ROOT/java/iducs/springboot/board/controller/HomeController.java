package iducs.springboot.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductReview;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.service.OrderInfoService;
import iducs.springboot.board.service.ProductReviewService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.SectionService;

@Controller
public class HomeController {
	@Autowired SectionService sectionService;
	@Autowired ProductService productService;
	@Autowired ProductReviewService reviewService;
	@Autowired OrderInfoService orderinfoService;
	
//	@GetMapping("/initdb") 
//	public String initialize() {
//		for(int i = 1;i <= 10; i++)
//			userService.saveUser(new User("a" + i, "p" + i, "name" + i, "contact" + i));	
//		for(int i = 1;i <= 5; i++) {
//			for(int j = 1; j <= 2; j++)
//			questionService.saveQuestion(new Question("제목 " + j, userService.getUserByUserId("u"+i) , "내용 " + j));
//		}
//		return "index";
//	}
	@GetMapping("")
	public void header(Model model) {
		List<Section> section=sectionService.getSection();
		model.addAttribute("section", section);
	}
	
	@GetMapping("/")
	public String home(
			Model model) {
		
		List<Product> product_last8 = productService.get8ProductOrderByNoDesc();
		List<Product> product_rand5 = productService.getRand5Product();
		List<Product> product_rand3 = productService.getRand3Product();
		List<Product> product_new_rand5 = productService.getNewRand5Product("2019-08-20");	// 날짜 이후에 등록된 상품(랜덤)
		List<Product> product_sale_rand5 = productService.getSaleRand5Product(30);			// 할인율이 30%이상인 상품(랜덤)
		List<OrderInfo> product_order_rand5 = orderinfoService.findOrderRand5(1);
		// 위 인터페이스의 매개변수는 판매 상품의 갯수를 카운트하는 쿼리를 기반으로 한다. 예를 들어 상품 a의 판매 갯수는 3개, 상품 b의 판매 갯수는 5개라고 치고 매개변수로 4를 넣으면
		// 이 쿼리의 결과값은 상품 b만 나오게 된다. 만약에 3을 넣으면 a,b 모두 나오게 된다.
		
		for(int i = 0; i<product_last8.size(); i ++) {	
			// 상품평을 종합하여 평균을 내는 작업, 만약 상품평이 한개도 없다면 점수는 5점(별 5개)로 통일
			List<ProductReview> review = reviewService.findByProductNo(product_last8.get(i).getNo());
			if(review.isEmpty() == false)
			{
				int before=0;
				for(int j = 0; j < review.size(); j ++) {
					before = before + review.get(j).getScore();
				}
				product_last8.get(i).setScore(before / (double)review.size());
			} else {
				product_last8.get(i).setScore(5);
			}
		}
		
		for(int i = 0; i<product_new_rand5.size(); i ++) {	
			List<ProductReview> review = reviewService.findByProductNo(product_new_rand5.get(i).getNo());
			if(review.isEmpty() == false)
			{
				int before=0;
				for(int j = 0; j < review.size(); j ++) {
					before = before + review.get(j).getScore();
				}
				product_new_rand5.get(i).setScore(before / (double)review.size());
			} else {
				product_new_rand5.get(i).setScore(5);
			}
		}
		
		for(int i = 0; i<product_sale_rand5.size(); i ++) {	
			List<ProductReview> review = reviewService.findByProductNo(product_sale_rand5.get(i).getNo());
			if(review.isEmpty() == false)
			{
				int before=0;
				for(int j = 0; j < review.size(); j ++) {
					before = before + review.get(j).getScore();
				}
				product_sale_rand5.get(i).setScore(before / (double)review.size());
			} else {
				product_sale_rand5.get(i).setScore(5);
			}
		}
		
		
		for (int i = 0; i < product_order_rand5.size(); i++) {
			List<ProductReview> review = reviewService.findByProductNo(product_order_rand5.get(i).getProduct().getNo());
			if (review.isEmpty() == false) {
				int before = 0;
				for (int j = 0; j < review.size(); j++) {
					before = before + review.get(j).getScore();
				}
				product_order_rand5.get(i).getProduct().setScore(before / (double) review.size());
			} else {
				product_order_rand5.get(i).getProduct().setScore(5);
			}
		}
		
		model.addAttribute("product_rand5", product_rand5);
		model.addAttribute("product_last8", product_last8);
		model.addAttribute("product_rand3", product_rand3);
		model.addAttribute("product_new_rand5", product_new_rand5);
		model.addAttribute("product_sale_rand5", product_sale_rand5);
		model.addAttribute("product_order_rand5", product_order_rand5);
		
		return "home/main/index";
	}
	
	@GetMapping("/about")
	public String about() {		
		return "home/main/about";
	}
	
	@GetMapping("/404")
	public String error() {		
		return "home/main/404";
	}

}
