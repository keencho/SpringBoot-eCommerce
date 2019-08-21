package iducs.springboot.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductSize;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.domain.Wishlist;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductSizeService;
import iducs.springboot.board.service.ProductStockService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.service.WishlistService;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductStockService productstockService;
	@Autowired
	ProductSizeService productsizeService;
	@Autowired
	WishlistService wishlistService;
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public String wishlist( 
			Model model, 
			HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/404";
		}
		User user = (User) session.getAttribute("user");
		List<Wishlist> wishlist = wishlistService.getWishlistByUserNo(user.getNo());
		
		model.addAttribute("wishlist", wishlist);
		return "home/feature/wishlist";
	}
	
	@GetMapping("/complete")
	public String wishlistComplete() {
		return "home/feature/wishlistMove";
	}
	
	@GetMapping("/already")
	public String wishlistAlready() {
		return "home/feature/wishlistAlready";
	}
	
	@ResponseBody
	@PostMapping("/add")
	public int wishlistAdd(
			@RequestParam(value = "userno") long userno,
			@RequestParam(value = "productno") long productno, 
			HttpSession session) {
		Product product = productService.getProductById(productno);
		User user = userService.getUserByNo(userno);
		
		Wishlist wishlistduplicate = wishlistService.getWishlistDuplicateCheck(productno, userno);
		if(wishlistduplicate == null) {
			Wishlist wishlist= new Wishlist(product, user);
			wishlistService.saveWishlist(wishlist);
			return 0;
		} else {
			return 1;
		}
	}
	
	@GetMapping("/option/{no}")
	public String cartGetOption(@PathVariable(value = "no") long no, Model model) {
		Product product = productService.getProductById(no);
		List<ProductStock> sizeStock = productstockService.findSizeByProductNo(no);
		List<ProductStock> colorStock = productstockService.findColorByProductNo(no);
		model.addAttribute("product", product);
		model.addAttribute("size", sizeStock);
		model.addAttribute("color", colorStock);
		try  {
			ProductSize productSize = productsizeService.getProductSizeByNoNativeQuery(no);
			model.addAttribute("productSize", productSize);
		} catch (Exception e) {
			System.out.println("null~"); // null 체크용 try~catch문
		}
		return "home/feature/wishlistOption";
	}
	
	@GetMapping("/del/{userno}/{no}")
	public String wishlistDel(
			@PathVariable (value = "userno") Long userno,
			@PathVariable (value ="no") Long no, 
			Model model, 
			HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/404";
		}
		Wishlist wishlist = wishlistService.getWishlistByNo(no);
		wishlistService.deleteWishlist(wishlist);
		return "redirect:/wishlist";
	}
	
	@GetMapping("/delall/{userno}")
	public String wishlistDelAll(
			@PathVariable (value = "userno") Long userno, 
			Model model, 
			HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/404";
		}
		wishlistService.deleteByIdWishList(userno);
		return "redirect:/wishlist";
	}
}
