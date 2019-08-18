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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Order;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.OrderInfoService;
import iducs.springboot.board.service.OrderService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	@GetMapping("")
	public String orderHome(Model model) {
		List<Order> order = orderService.findAll();
		model.addAttribute("order", order);
		return "admin/order/main";
	}

	@ResponseBody
	@PostMapping("/statusUpdate")
	public void orderStatusUpdate(
			@RequestParam(value="no") long no,
			@RequestParam(value="status") int status,
			Model model){
		Order order = orderService.findByNo(no);
		order.setStatus(status);
		orderService.updateOrder(order);	
	}

}
