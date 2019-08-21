package iducs.springboot.board.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Checkout;
import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Color;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.domain.UserAddress;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ColorService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.UserAddressService;
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
	@Autowired ProductService productService;
	@Autowired ClothesSizeService sizeService;
	@Autowired ColorService colorService;
	@Autowired UserService userService;
	@Autowired UserAddressService addressService;

	@GetMapping("/getForm")
	public String checkoutForm(Model model, HttpSession session) {
		if(session.getAttribute("info") == null) {
			return "redirect:/404";
		}
		if(session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			List<UserAddress> address = addressService.getAddressByUserNo(user.getNo());
			model.addAttribute("address", address);
		}
		
		String deliveryDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("MM월 dd일(E)");
		cal.add(Calendar.DATE,+3);
		deliveryDate = formatDate.format(cal.getTime());
		model.addAttribute("date", deliveryDate);
		return "home/checkout/checkoutForm";
	}
	
	@ResponseBody
	@PostMapping("/jsonData")
	public int cartOptionStockCheck(
			@RequestBody List<Map<String, Object>> productData,
			HttpSession session,
			Model model) {
		List<Checkout> info = new ArrayList<Checkout>();
		for(int i = 0; i < productData.size(); i ++) {
			Checkout checkout = new Checkout();
			String productnoStr = (String) productData.get(i).get("no");
			String sizenoStr = (String) productData.get(i).get("size");
			String colornoStr = (String) productData.get(i).get("color");
			String qtyStr = (String) productData.get(i).get("qty");
			
			Long productNo = Long.parseLong(productnoStr);
			Long sizeNo = Long.parseLong(sizenoStr);
			Long colorNo = Long.parseLong(colornoStr);
			Long qty = Long.parseLong(qtyStr);
			
			Color color = colorService.getColorByNo(colorNo);
			ClothesSize size = sizeService.getClothesSizeByNo(sizeNo);
			
			Product product = productService.getProductById(productNo);
			
			String priceStr = product.getListprice();
			Long price = Long.parseLong(priceStr);
			
			checkout.setNo(product);
			checkout.setSize(size.getName());
			checkout.setSizeNo(sizeNo);
			checkout.setColor(color.getName());
			checkout.setColorNo(colorNo);
			checkout.setPrice(price);
			checkout.setQty(qty);
			info.add(checkout);
			
		}
		session.setAttribute("info", info);
		
		return 1;
	}
}
