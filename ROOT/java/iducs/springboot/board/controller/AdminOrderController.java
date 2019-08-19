package iducs.springboot.board.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.domain.Section;
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
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE,-7);
		Date d = new Date();
		String weekago = formatDate.format(cal.getTime());
		String today = formatDate.format(d);
		
		model.addAttribute("date1", weekago);
		model.addAttribute("date2", today);
		model.addAttribute("order", order);
		return "admin/order/main";
	}
	@GetMapping("/{date1}/{date2}/{status}")
	public String orderDateFind(
			@PathVariable(value="date1") String date1,
			@PathVariable(value="date2") String date2,
			@PathVariable(value="status") int status,
			Model model
			) {
		if (status == -1) {
			List<Order> order = orderService.findByDateBetween(date1, date2);
			model.addAttribute("order", order);
		} else {
			List<Order> order = orderService.findByStatusAndDateBetween(status, date1, date2);
			model.addAttribute("order", order);
		}
		
		model.addAttribute("date2", date1);
		model.addAttribute("date2", date2);
		model.addAttribute("status", status);
		return "admin/order/main";
	}
	
	@GetMapping("/view/{no}")
	public String orderViewInvoice(
			@PathVariable(value="no") long no,
			Model model
			) {
		Order order = orderService.findByNo(no);
		List<OrderInfo> info = orderInfoService.findByOrderNo(no);
		
		for (int i = 0; i < info.size(); i++) {
			info.get(i).setInt_price(Integer.parseInt(info.get(i).getPrice()));
		}
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat(order.getDate());
		cal.add(Calendar.DATE, +3);
		
		model.addAttribute("order", order);
		model.addAttribute("info", info);
		model.addAttribute("limit", formatDate.format(cal.getTime()));
		return "admin/order/view";
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
	
	@GetMapping("/del/{no}")
	public String orderDelete(@PathVariable(value = "no") Long no, Model model) {
		Order order = orderService.findByNo(no);
		orderService.deleteOrder(order);
		return "redirect:/admin/order";
	}

}
