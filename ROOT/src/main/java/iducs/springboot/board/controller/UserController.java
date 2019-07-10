package iducs.springboot.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.QuestionEntity;
import iducs.springboot.board.entity.UserEntity;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.utils.HttpSessionUtils;

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
	public int idCheck(@RequestParam(value = "id") String id) {
		int result=0;
		User user1 = userService.getUserById(id);
		if(user1!=null) result=1;
		// User getUserByNo(long no); // primary key에 해당하는 id로  조회
		return result;
	}
	@GetMapping("/login")
	public String loginForm(HttpSession session) {
		User checksession = (User) session.getAttribute("user");
		if(checksession != null) {
			return "redirect:../404";
		}
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
		return "redirect:../";
	}
	@GetMapping("/loginError")
	public String loginError() {
		return "user/loginError";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../";
	}
	@GetMapping("")
	public String getUsers(Model model, HttpSession session, @PageableDefault(size=3, sort="id", direction = Sort.Direction.ASC) Pageable pageable) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login-form";
		}
		List<User> user = userService.getUsersByPage(pageable);
		Page<UserEntity> pageNo = userService.getUserPage(pageable);
		model.addAttribute("users", user);
		model.addAttribute("pageNo", pageNo);
		return "/users/list";
	}	
	/*
	 * @GetMapping("/{id}") public String getUserById(@PathVariable(value = "id")
	 * Long id, Model model) { User user = userService.getUserById(id);
	 * model.addAttribute("user", user); return "/users/info"; }
	 */
	
//	@PutMapping("/{id}")
//	public String updateUserById(@PathVariable(value = "id") Long id, @Valid User formUser, Model model, HttpSession session) {
//		User user = userService.getUserById(id);
//		user.setUserPw(formUser.getUserPw());
//		user.setName(formUser.getName());
//		user.setCompany(formUser.getCompany());
//		userService.updateUser(user);		
//		model.addAttribute("user", user);
//		session.setAttribute("user", user);
//		return "/users/info";
//	}	
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable(value = "id") Long id, @Valid User formUser, Model model, HttpSession session) {
		userService.deleteUser(formUser);
		model.addAttribute("name", formUser.getName());
		session.invalidate();
		return "redirect:/";
	}
}
