package iducs.springboot.board.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController{
	@Autowired UserService userService;	
	
	@GetMapping("")
	public String adminUserHome(
			Model model) {
		List<User> user = userService.getUsers();
		
		model.addAttribute("user", user);
		return "admin/user/list";
	}
	
	@GetMapping("/update/{rank}/{no}")
	public String adminUserUpdate(
			@PathVariable("rank") String rank,
			@PathVariable("no") Long no,
			Model model
			) {
		User user = userService.getUserByNo(no);
		System.out.println(rank);
		if(rank.equals("to_user")) {
			user.setRank("1");
		} else if (rank.equals("to_admin")) {
			user.setRank("0");
		}
		userService.updateUser(user);
		
		return "redirect:/admin/user";
	}
	
	@GetMapping("/view/{no}")
	public String adminUserView(
			@PathVariable("no") Long no,
			Model model
			) {
		User user = userService.getUserByNo(no);
		model.addAttribute("user", user);
		
		return "admin/user/view";
	}
	
	@DeleteMapping("/del/{no}")
	public String adminUserDelete(
			@PathVariable("no") Long no,
			Model model
			){
		User user = userService.getUserByNo(no);
		userService.deleteUser(user);
		
		return "redirect:/admin/user";
	}
	
}
