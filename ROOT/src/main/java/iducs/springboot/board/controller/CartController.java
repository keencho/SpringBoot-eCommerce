package iducs.springboot.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.domain.Section;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductStockService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductStockService productstockService;
	
	@GetMapping("")
	public String cart(Model model) {
		return "home/feature/cart";
	}
	
	@ResponseBody
	@PostMapping("/option")
	public int cartOption(@RequestParam(value = "no") long productno) { // Ajax product 결과값 체크
		int result = 0;
		Product productoption = productService.getProductById(productno);
		if (productoption != null)
			result = 1;
		return result;
	}
	
	@GetMapping("/option/{no}")
	public String cartGetOption(@PathVariable(value = "no") long no, Model model) {
		Product product = productService.getProductById(no);
		List<ProductStock> sizeStock = productstockService.findSizeByProductNo(no);
		List<ProductStock> colorStock = productstockService.findColorByProductNo(no);
		model.addAttribute("product", product);
		model.addAttribute("size", sizeStock);
		model.addAttribute("color", colorStock);
		return "home/feature/cartOption";
	}
	
	@ResponseBody
	@PostMapping("/option/stockCheck")
	public int cartOptionStockCheck(
			@RequestParam(value = "no") long no,
			@RequestParam(value = "color") long color,
			@RequestParam(value = "size") long size
			) { // Ajax product 결과값 체크
		ProductStock stock = productstockService.stockCheck(no, color, size);
		ProductStock stockResult = productstockService.getProductStockById(stock.getNo());
		int result = Integer.parseInt(stockResult.getStock());
		return result;
	}

}
