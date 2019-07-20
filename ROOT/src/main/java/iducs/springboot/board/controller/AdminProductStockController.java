package iducs.springboot.board.controller;

import java.util.List;
import java.util.Random;

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
import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Color;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ColorService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductStockService;

@Controller
@RequestMapping("/admin/product/stock")
public class AdminProductStockController {
	@Autowired ClothesSizeService clothessizeService;
	@Autowired ProductService productService;
	@Autowired ProductStockService productstockService;
	@Autowired ColorService colorService;
	
	@GetMapping("/add/{no}")
	public String productStockList(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		model.addAttribute("product", product);
		return "admin/product/stock/addstock";
	}
	
	@GetMapping("/addForm/{no}")
	public String productStockAddForm(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		model.addAttribute("product", product);
		Random randomGenerator = new Random();
		int start = 100;
		int end = 500;
		double range = end - start + 1;
		int randomInt5to10 = (int)(randomGenerator.nextDouble() * range + start);
		model.addAttribute("random", randomInt5to10);

		return "admin/product/stock/addstockForm";
	}

	@GetMapping("/findSize")
	public String productStockFindSize(Model model) {
		List<ClothesSize> size = clothessizeService.getClothesSize();
		model.addAttribute("size", size);
		return "admin/product/stock/findSize";
	}
	
	@GetMapping("/findColor")
	public String productStockFindColor(Model model) {
		List<Color> color = colorService.getColor();
		model.addAttribute("color", color);
		return "admin/product/stock/findColor";
	}

	
	@PostMapping("/add")
	public String productSizeAddPost(Long product_no, Long size_no, Long color_no, String stock, Model model) {
		Product product = productService.getProductById(product_no);
		ClothesSize clothessize = clothessizeService.getClothesSizeByNo(size_no);
		Color color = colorService.getColorByNo(color_no);

		ProductStock productstock= new ProductStock(product, clothessize, color, stock);
		productstockService.saveProductStock(productstock);
		return "redirect:/admin/product/stock/add/" + product_no;
	}
	  
	
	@GetMapping("/update/{no}")
	public String productSizeUpdateSize(@PathVariable(value = "no") Long no, Model model) {
		ProductStock stock = productstockService.getProductStockById(no);
		model.addAttribute("stock", stock);
		return "admin/product/stock/updateStock";
	}

	@PutMapping("/update/{no}")
	public String productSizeUpdateSizePut(@PathVariable(value = "no") Long no, Long productno, Long size_no, Long color_no,
			@Valid ProductStock updateProductStock, Model model) {
		ClothesSize clothessize = clothessizeService.getClothesSizeByNo(size_no);
		Color color = colorService.getColorByNo(color_no);
		ProductStock productstock = productstockService.getProductStockById(no);

		productstock.setSize_no(clothessize);
		productstock.setColor_no(color);
		productstock.setStock(updateProductStock.getStock());

		productstockService.updateProductStock(productstock);
		return "redirect:/admin/product/stock/add/" + productno;
	}

	@GetMapping("/del/{productno}/{no}")
	public String productStockDel(@PathVariable(value = "productno") Long productno, @PathVariable(value = "no") Long no,
			Model model) {
		ProductStock stock = productstockService.getProductStockById(no);
		productstockService.deleteProductStock(stock);
		return "redirect:/admin/product/stock/add/" + productno;
	}
	 
	 
	 
}
