package iducs.springboot.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.User;
import iducs.springboot.board.domain.UserAddress;
import iducs.springboot.board.service.UserAddressService;
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired UserService userService; 
	@Autowired UserAddressService userAddressService;
	
	@GetMapping("/register")
	public String createUserForm(Model model) {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("date", date.format(today));
		return "home/user/register";
	}
	@PostMapping("/register")
	public String createUser(
			String name,
			String email,
			String id,
			String phone,
			String joinday,
			String rank,
			String password,
			String zipcode,
			String address,
			String detailaddress,
			String reference,
			Model model) {
		
		User user = new User(id, password, name, email, phone, rank, joinday, 0);
		userService.saveUser(user);
		
		User userInfo = userService.getUserById(id);
		UserAddress userAddress = new UserAddress(userInfo, name, phone, zipcode, address, detailaddress, reference, 0);
		userAddressService.saveUserAddress(userAddress);
		return "home/user/complete";
	}
	@ResponseBody
	@PostMapping("/idCheck")
	public int idCheck(@RequestParam(value = "id") String id) {	// Ajax id 중복체크
		int result=0;
		User user1 = userService.getUserById(id);
		if(user1!=null) result=1;
		return result;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "home/user/login";
	}
	
	@GetMapping("/loginPopup")
	public String loginPopupForm() {
		return "home/user/loginPopup";
	}
	@PostMapping("/login")
	public String login(@Valid User user, HttpSession session) {
		System.out.println("login process : ");
		User sessionUser = userService.getUserById(user.getId()); 
		if(sessionUser == null) {
			System.out.println("id error : ");
			return "redirect:loginError";
		}
		if(!sessionUser.getPassword().equals(user.getPassword())) {
			System.out.println("pw error : ");
			return "redirect:loginError";
		}
		session.setAttribute("user", sessionUser);
		session.setAttribute("userRank", sessionUser.getRank());
		System.out.println(sessionUser.getRank());
		return "redirect:../";
	}
	
	@ResponseBody
	@PostMapping("/loginPopup")
	public int loginPopup(
			@RequestParam(value = "id") String id,
			@RequestParam(value = "pw") String pw, 
			HttpSession session) {
		User sessionUser = userService.getUserById(id);
		if(sessionUser == null) {
			return 1;
		}
		if(!sessionUser.getPassword().equals(pw)) {
			return 2;
		}
		session.setAttribute("user", sessionUser);
		session.setAttribute("userRank", sessionUser.getRank());
		return 0;
	}
	
	@GetMapping("/loginError")
	public String loginError() {
		return "home/user/loginError";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.setContentType("text/html; charset=UTF-8");
		/*
		 * PrintWriter out = response.getWriter();
		 * out.println("<script>alert('로그아웃이 완료되었습니다.'); location.href='/';</script>");
		 * out.flush();
		 */
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/addAddress")
	public Long addAddress(
			@RequestParam(value = "user_no") long user_no,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "zipcode") String zipcode,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "detailaddress") String detailaddress,
			@RequestParam(value = "reference") String reference
			) {
		User user = userService.getUserByNo(user_no);
		UserAddress userAddress = new UserAddress(user, name, phone, zipcode, address, detailaddress, reference, 1);
		userAddressService.saveUserAddress(userAddress);
		UserAddress getLastIndex = userAddressService.getAddressByUserNoOrderByDesc(user_no);
		return getLastIndex.getNo();
	}
	
	@ResponseBody
	@PostMapping("/checkAddress")
	public int checkAddress(
			@RequestParam(value = "user_no") long no	
			) {
		List<UserAddress> address = userAddressService.getAddressByUserNo(no);
		System.out.println(address.size());
		return address.size();
	}
	
	@ResponseBody
	@PostMapping("/checkPoint")
	public int checkPoint(
			@RequestParam(value = "no") long no,
			@RequestParam(value = "point") int point
			) {
		User user = userService.getUserByNo(no);
		if(user.getPoint() < 2000 || user.getPoint() < point) {
			return 1;
		}

		if(user.getPoint() >= 2000 && user.getPoint() >= point) {	// 고객의 포인트가 2000보다 많고 입력한 포인트값이 보유한 포인트 값보다 작을때 --> 성공
			return 0;
		}
		return 0;
	}
	
	@ResponseBody
	@PostMapping("/updateAddress")
	public int updateAddress(
			@RequestParam(value = "no") long no,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "zipcode") String zipcode,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "detailaddress") String detailaddress,
			@RequestParam(value = "reference") String reference
			) {
		UserAddress userAddress = userAddressService.getAddressByNo(no);
		userAddress.setName(name);
		userAddress.setPhone(phone);
		userAddress.setZipcode(zipcode);
		userAddress.setAddress(address);
		userAddress.setDetailaddress(detailaddress);
		userAddress.setReference(reference);
		userAddressService.updateUserAddress(userAddress);
		return 1;
	}
}
