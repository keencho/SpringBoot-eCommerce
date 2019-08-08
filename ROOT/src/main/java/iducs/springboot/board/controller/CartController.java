package iducs.springboot.board.controller;

import java.util.ArrayList;
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
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductSizeService;
import iducs.springboot.board.service.ProductStockService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductStockService productstockService;
	@Autowired
	ProductSizeService productsizeService;
	
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
		try  {
			ProductSize productSize = productsizeService.getProductSizeByNoNativeQuery(no);
			model.addAttribute("productSize", productSize);
		} catch (Exception e) {
			System.out.println("null~"); // null 체크용 try~catch문
		}
		return "home/feature/cartOption";
	}
	
	@GetMapping("/option/update/{no}")
	public String cartGetUpdateOption(@PathVariable(value = "no") long no, Model model) {
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
		return "home/feature/cartUpdateOption";
	}

	@GetMapping("/sizetable/{no}")
	public String cartGetSizeTable(@PathVariable(value = "no") long no, Model model) {
		Product product = productService.getProductById(no);
		ProductSize productSize = productsizeService.getProductSizeByNoNativeQuery(no);
		model.addAttribute("clothesSize", productSize);
		model.addAttribute("product", product);
		return "home/feature/cartSizetable";
	}
	
	@GetMapping("/move")
	public String cartMove() {
		return "home/feature/cartMove";
	}
	
	@GetMapping("/del/{no}")
	public String cartDel(@PathVariable(value = "no") int no, Model model, HttpSession session) {
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		int total = 0;
		cart.remove(no);
		try {
			for(int i = 0; i< cart.size(); i ++) {
				if(no < cart.get(no + i).getCartNo()) {
					cart.get(no + i).setCartNo(cart.get(no+i).getCartNo() -1);
				}
			}
		} catch (Exception e) {
		}
		for(int i = 0; i< cart.size(); i ++) {
			total = total + (cart.get(i).getCartPrice() * cart.get(i).getCartQty());
		}
		session.setAttribute("total", total);
		return "redirect:/cart";
	}
	
	@GetMapping("/deleteall")
	public String cartDeleteAll(HttpSession session) {
		session.removeAttribute("cart");
		session.removeAttribute("total");
		return "redirect:/cart";
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
	
	@ResponseBody
	@PostMapping("/add")
	public void cartAdd(
			@RequestParam(value = "no") long no,
			@RequestParam(value = "color") String color,
			@RequestParam(value = "size") String size,
			@RequestParam(value = "qty") int qty,
			HttpSession session
			) {
		Product product = productService.getProductById(no);
		int cartno = 0;
		product.setCartSize(size); // domain에 cart가 붙어있으면 db용이 아닌 장바구니용(따라서 entity에는 이에대한 변수가 존재하지 않음)
		product.setCartColor(color);
		product.setCartQty(qty);
		product.setCartPrice(Integer.parseInt(product.getListprice()));
		product.setCartNo(cartno);
		int total = product.getCartPrice() * product.getCartQty(); // 이 total은 
		if(session.getAttribute("cart") == null) { // cart session이 존재하지 않으면 새로 생성
			List<Product> cart = new ArrayList<Product>();
			cart.add(cartno, product);
			session.setAttribute("cart", cart);
			session.setAttribute("total", total);
		} else { // cart session이 존재하면
			List<Product> cart = (List<Product>) session.getAttribute("cart"); // cart session의 정보를 불러옴
			total = (int)session.getAttribute("total");
			for(int i = 0; i < cart.size(); i++) {
				cartno = i + 1; // 새로 삽일될 정보의 인덱스는 cart session의 마지막 인덱스 +1
				product.setCartNo(cartno);
			}
			int index = this.exists(no, cart); // 장바구니에 현재 고객이 선택한 상품이 있는지 확인
			if(index == -1) { // 장바구니에 현재 고객이 선택한 상품이 없으면..
				cart.add(cartno,  product);				
			} else {  // 장바구니에 현재 고객이 선택한 상품이 있으면 장바구니에 들어있는 상품의 수량 +1(이 경우 옵션은 기존의 옵션을 채택)
					  // list에서 선택했을때는 옵션 고려 하지 않지만 후에 view 페이지에서 옵션선택시 기존의 옵션을 채택할지 새로운 옵션을 채택할지 다시 생각하기
				int quantity = cart.get(index).getCartQty() + qty;
				cart.get(index).setCartQty(quantity);
			}
			total = total + (product.getCartQty() * product.getCartPrice());
			session.setAttribute("cart", cart);
			session.setAttribute("total", total);
		}
	}
	
	@ResponseBody
	@PostMapping("/updateOption")
	public void cartUpdateOption(
			@RequestParam(value = "no") int no,
			@RequestParam(value = "color") String color,
			@RequestParam(value = "size") String size,
			HttpSession session
			) {
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		cart.get(no).setCartColor(color);
		cart.get(no).setCartSize(size);
	}
	
	@ResponseBody
	@PostMapping("/updateqty")
	public void cartQtyUpdate(
			@RequestParam(value = "no") int no,
			@RequestParam(value = "qty") int qty,
			HttpSession session
			) {
		List<Product> cart = (List<Product>) session.getAttribute("cart");
		int total = (int) session.getAttribute("total");
		int originalQty = cart.get(no).getCartQty();
		cart.get(no).setCartQty(qty);
		total = total + ((qty - originalQty) * cart.get(no).getCartPrice());
		session.setAttribute("total", total);
		System.out.println(qty);
	}
	 
	private int exists(Long id, List<Product> cart) { // 장바구니에 이미 선택한 상품이 있는지 확인
		for(int i = 0; i < cart.size(); i++) {
			if ( cart.get(i).getNo() == id){
				return i;
			}
		}
		return -1;
	}

}
