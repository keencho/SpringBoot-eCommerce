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
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductSizeService;

@Controller
@RequestMapping("/admin/product/size")
public class AdminProductSizeController {
	@Autowired ClothesSizeService clothessizeService;
	@Autowired ProductService productService;
	@Autowired ProductSizeService productsizeService;
	
	@GetMapping("/add/{no}")
	public String productSizeAdd(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		model.addAttribute("product", product);
		return "admin/product/size/addsize";
	}
	
	@GetMapping("/addForm/{no}")
	public String productSizeAddForm(@PathVariable(value = "no") Long no, Model model) {
		Product product = productService.getProductById(no);
		 System.out.println(product.getProductsize().size());
		model.addAttribute("product", product);
		return "admin/product/size/addsizeForm";
	}
	
	@GetMapping("/findSize")
	public String productSizeFindSize(Model model) {
		List<ClothesSize> size = clothessizeService.getClothesSize();
		model.addAttribute("size", size);
		return "admin/product/size/findSize";
	}
	
	@PostMapping("/add")
	public String productSizeAddPost(Long product_no, Long size_no, String top_length, String top_shoulder, String top_sleeve, String top_chest, String bot_length, String bot_hip, String bot_rise, String bot_thigh, String bot_under, String bot_waist, Model model) {
		Product product = productService.getProductById(product_no);
		ClothesSize clothessize = clothessizeService.getClothesSizeByNo(size_no);
				
		ProductSize size = new ProductSize(product, clothessize, top_length, top_shoulder, top_chest, top_sleeve, bot_hip, bot_length, bot_rise, bot_thigh, bot_under, bot_waist);
		productsizeService.saveProductSize(size);
		return "redirect:/admin/product/size/add/" + product_no;
	}
	
	@GetMapping("/update/{no}")
	public String productSizeUpdateSize(@PathVariable(value="no") Long no, Model model) {
		ProductSize size = productsizeService.getProductSizeByNo(no);
		model.addAttribute("size", size);
		return "admin/product/size/updateSize";
	}
	
	@PutMapping("/update/{no}")
	public String productSizeUpdateSizePut(@PathVariable(value = "no") Long no, Long productno, Long size_no, @Valid ProductSize updateProductSize, Model model) {
		ClothesSize clothessize = clothessizeService.getClothesSizeByNo(size_no);
		ProductSize productsize = productsizeService.getProductSizeByNo(no);
		
		productsize.setSize_no(clothessize);
		
		productsize.setTop_length(updateProductSize.getTop_length());
		productsize.setTop_sleeve(updateProductSize.getTop_sleeve());
		productsize.setTop_chest(updateProductSize.getTop_chest());
		productsize.setTop_shoulder(updateProductSize.getTop_shoulder());
		
		productsize.setBot_length(updateProductSize.getBot_length());
		productsize.setBot_under(updateProductSize.getBot_under());
		productsize.setBot_thigh(updateProductSize.getBot_thigh());
		productsize.setBot_hip(updateProductSize.getBot_hip());
		productsize.setBot_rise(updateProductSize.getBot_rise());
		productsize.setBot_waist(updateProductSize.getBot_waist());
		
		productsizeService.updateProductSize(productsize);
		return "redirect:/admin/product/size/add/" + productno;
	}
	
	@GetMapping("/del/{productno}/{no}")
	public String productSizeDel(@PathVariable(value="productno") Long productno, @PathVariable(value = "no") Long no, Model model) {
		ProductSize product = productsizeService.getProductSizeByNo(no);
		productsizeService.deleteProductSize(product);
		return "redirect:/admin/product/size/add/" + productno;
	}

}
