package iducs.springboot.board.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Color;
import iducs.springboot.board.domain.Order;
import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ColorService;
import iducs.springboot.board.service.OrderInfoService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.ProductStockService;
import iducs.springboot.board.service.UserAddressService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired ProductService productService;
	@Autowired ClothesSizeService sizeService;
	@Autowired ColorService colorService;
	@Autowired UserService userService;
	@Autowired UserAddressService addressService;
	@Autowired OrderService orderService;
	@Autowired OrderInfoService orderInfoService;
	@Autowired ProductStockService stockService;

	@ResponseBody
	@PostMapping("/deposit")
	public void orderDeposit(
			@RequestParam(value="order_name") String order_name,
			@RequestParam(value="order_phone") String order_phone,
			@RequestParam(value="order_address") String order_address,
			@RequestParam(value="user_no") Long user_no,
			@RequestParam(value="order_message") String order_message,
			@RequestParam(value="account_bank") int account_bank,
			@RequestParam(value="account_name") String account_name,
			@RequestParam(value="point") int point,
			@RequestParam(value="total_price") String total_price,
			@RequestParam(value="order_password") String order_password,
			HttpSession session
			) {
		Date d = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		String orderno = this.produceOrderno();
		User user = userService.getUserByNo(user_no);

		if (user_no != 0) { // 회원일 경우
			if (point != 0) {	// 포인트 차감
				user.setPoint(user.getPoint()-point);
				userService.updateUser(user);
			}
			Order order = new Order(orderno, order_name, order_phone, order_address, 0, null, user, order_message, 1, null, null, null, account_bank, account_name, 1, today.format(d), total_price);
			orderService.saveOrder(order);
		} else if (user_no == 0) {	// 비회원일 경우
			Order order = new Order(orderno, order_name, order_phone, order_address, 1, order_password, userService.getUserByNo(-1), order_message, 1, null, null, null, account_bank, account_name, 1, today.format(d), total_price);
			orderService.saveOrder(order);
		}
		session.removeAttribute("cart");
		session.removeAttribute("total");
	}
	
	@ResponseBody
	@PostMapping("/card")
	public void orderCard(
			@RequestParam(value="order_name") String order_name,
			@RequestParam(value="order_phone") String order_phone,
			@RequestParam(value="order_address") String order_address,
			@RequestParam(value="user_no") Long user_no,
			@RequestParam(value="order_message") String order_message,
			@RequestParam(value="point") int point,
			@RequestParam(value="total_price") String total_price,
			@RequestParam(value="order_password") String order_password,
			@RequestParam(value="card_id") String card_id,
			@RequestParam(value="card_shopid") String card_shopid,
			@RequestParam(value="card_applyno") String card_applyno,
			HttpSession session
			) {
		Date d = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		String orderno = this.produceOrderno();
		User user = userService.getUserByNo(user_no);

		if (user_no != 0) { // 회원일 경우
			if (point != 0) {	// 포인트 차감
				user.setPoint(user.getPoint()-point);
				userService.updateUser(user);
			}
			Order order = new Order(orderno, order_name, order_phone, order_address, 0, order_password, user, order_message, 0, card_id, card_shopid, card_applyno, 0, null, 0, today.format(d), total_price);
			orderService.saveOrder(order);
		} else if (user_no == 0) {	// 비회원일 경우
			Order order = new Order(orderno, order_name, order_phone, order_address, 1, order_password, userService.getUserByNo(-1), order_message, 0, card_id, card_shopid, card_applyno, 0, null, 0, today.format(d), total_price);
			orderService.saveOrder(order);
		}
		session.removeAttribute("cart");
		session.removeAttribute("total");
	}
	
	@ResponseBody
	@PostMapping("/info/add")
	public void orderInfoAdd(
		@RequestParam(value="order_no") long order_no,
		@RequestParam(value="product_no") long product_no,
		@RequestParam(value="color_no") long color_no,
		@RequestParam(value="size_no") long size_no,
		@RequestParam(value="qty") int qty,
		@RequestParam(value="price") String price
		) {
		Order order = orderService.findByNo(order_no);
		Product product = productService.getProductById(product_no);
		Color color = colorService.getColorByNo(color_no);
		ClothesSize size = sizeService.getClothesSizeByNo(size_no);
		ProductStock stock = stockService.stockCheck(product_no, color_no, size_no);
		
		int original_stock = Integer.parseInt(stock.getStock());		// 구매 수량만큼 재고에서 빼기
		String result_stock = Integer.toString(original_stock - qty);
		stock.setStock(result_stock);
		stockService.updateProductStock(stock);
		
		OrderInfo info = new OrderInfo(order, product, color, size, qty, price, 0);
		orderInfoService.saveOrderInfo(info);
	}
	
	@ResponseBody
	@PostMapping("/get/lastOrder")
	public long getLastOrderNo() {	// 마지막 인덱스 정보 얻기
		List<Order> order = orderService.findAll();
		return  order.get(order.size()-1).getNo();
	}
	
	@GetMapping("/complete/{no}")
	public String orderComplete (
			@PathVariable(value="no") long no,
			Model model
			){
		String deliveryDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("MM월 dd일(E)");
		cal.add(Calendar.DATE,+3);
		deliveryDate = formatDate.format(cal.getTime());
		
		Order order = orderService.findByNo(no);
		model.addAttribute("info", order);
		model.addAttribute("date", deliveryDate);
		
		return "home/checkout/checkoutComplete";
	}
	
	public String produceOrderno() {	// 주문번호 체크->생성
		Date d = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat todayOrder = new SimpleDateFormat("yyMMdd");
		String todayString = today.format(d);
		List<Order> order = orderService.findByDate(todayString);
		long orderno_long = 1;
		if (order.isEmpty() == false) {
			String orderno_before = order.get(order.size()-1).getOrderno();
			int split = orderno_before.indexOf("_");
			long orderno_after = Long.parseLong(orderno_before.substring(split+1));
			orderno_long = orderno_after + 1;
		}
		String orderno = todayOrder.format(d) + "_" + orderno_long;
	

		return orderno;
	}
}
