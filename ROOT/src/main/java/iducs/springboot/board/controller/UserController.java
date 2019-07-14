package iducs.springboot.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired UserService userService; 
	// 의존성 주입(Dependency Injection)
	// @Component, @Controller, @Repository, @Service 표시된 클래스형 빈 객체를 스프링이 스캔하여 등록하고, @Autowired 등 요청시 주입 	
	@GetMapping("/register")
	public String createUserForm(Model model) {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("date", date.format(today));
		return "user/register";
	}
	@PostMapping("/register")
	public String createUser(@Valid User formUser, Model model) {
		userService.saveUser(formUser); 
		model.addAttribute("user", formUser);
		return "user/complete";
	}
	@ResponseBody
	@PostMapping("/idCheck")
	public int idCheck(@RequestParam(value = "id") String id) {	// Ajax id 중복체크
		int result=0;
		User user1 = userService.getUserById(id);
		if(user1!=null) result=1;
		// User getUserByNo(long no); // primary key에 해당하는 id로  조회
		return result;
	}
	@GetMapping("/login")
	public String loginForm() {
		return "user/login";
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
	@GetMapping("/loginError")
	public String loginError() {
		return "user/loginError";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃이 완료되었습니다.'); location.href='/';</script>");
		out.flush();
		return null;
	}
}
