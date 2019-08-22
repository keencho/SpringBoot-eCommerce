package iducs.springboot.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import iducs.springboot.board.domain.ClothesSize;
import iducs.springboot.board.domain.Color;
import iducs.springboot.board.domain.Order;
import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.domain.ProductReview;
import iducs.springboot.board.domain.ProductStock;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.domain.UserAddress;
import iducs.springboot.board.service.ClothesSizeService;
import iducs.springboot.board.service.ColorService;
import iducs.springboot.board.service.OrderInfoService;
import iducs.springboot.board.service.OrderService;
import iducs.springboot.board.service.ProductQuestionService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.UserAddressService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.service.ProductReviewService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderInfoService orderinfoService;
	@Autowired
	UserAddressService useraddressService;
	@Autowired
	ProductQuestionService questionService;
	@Autowired
	ColorService colorService;
	@Autowired
	ClothesSizeService sizeService;
	@Autowired
	ProductReviewService reviewService;
	
	String osName = System.getProperty("os.name");
	static File cwd = new File("src/main/resources/static/uploads/review");	// 윈도우 업로드 경로
	static File cwd2 = new File("webapps/ROOT/WEB-INF/classes/static/uploads/review");	// 리눅스 실제 웹서버 업로드 경로
	static File path = cwd.getAbsoluteFile();
	static File path2 = cwd2.getAbsoluteFile();
	static String autoFolderStatic = path.toString();
	static String autoFolderStatic2 = path2.toString();
	
	Date d = new Date();
	SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
	
	String newname = null, newname2 = null, newname3 = null;
	
	@GetMapping("")
	public String mypageMain(
			@RequestParam(value="start", required=false) String date1,
			@RequestParam(value="end", required=false) String date2,
			Model model, 
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		List<Integer> statusArrayList = new ArrayList<Integer>();
		statusArrayList.add(0);
		statusArrayList.add(1);
		statusArrayList.add(2);
		statusArrayList.add(3);
		statusArrayList.add(4);
		statusArrayList.add(9);
		if(date1 == null && date2 == null) {
			List<Order> order = orderService.findByUserNoOrder(user.getNo(), statusArrayList);
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE,-7);
			Date d = new Date();
			String weekago = formatDate.format(cal.getTime());
			String today = formatDate.format(d);
			
			model.addAttribute("date1", weekago);
			model.addAttribute("date2", today);
			model.addAttribute("order", order);
		} else {
			List<Order> order = orderService.findByUserNoAndStatusAndDateBetween(user.getNo(), date1, date2, statusArrayList);
			
			model.addAttribute("date1", date1);
			model.addAttribute("date2", date2);
			model.addAttribute("order", order);
		}
		
		return "home/user/mypage/main";
	}

	@GetMapping("/order/update/{no}")
	public String mypageUpdateStatusForComplete(
			@PathVariable("no") long no,
			@RequestParam("point") int point,
			Model model,
			HttpSession session
			) {
		point = point / 1000;	// 적립될 포인트는 구매금액 / 1000(소숫점 버림)
		User sessionUser = (User) session.getAttribute("user");
		User user = userService.getUserByNo(sessionUser.getNo());
		int originalPoint = user.getPoint();
		int afterPoint = originalPoint + point;
		user.setPoint(afterPoint);
		userService.updateUser(user);	// 여기까진 고객의 포인트 적립
		
		Order order = orderService.findByNo(no);
		order.setStatus(9);
		orderService.updateOrder(order);	// 여기까진 주문 업데이트
		return "redirect:/mypage";
	}
	
	@GetMapping("/order/info/{no}")
	public String mypageOrderInfo(
			@PathVariable("no") long no,
			Model model
			) {
		Order order = orderService.findByNo(no);
		List<OrderInfo> orderInfo = orderinfoService.findByOrderNo(no);
		for (int i = 0; i < orderInfo.size(); i++) {
			orderInfo.get(i).setInt_price(Integer.parseInt(orderInfo.get(i).getPrice()));
		}
		model.addAttribute("order", order);
		model.addAttribute("info", orderInfo);
		return "home/user/mypage/orderInfo";
	}
	
	@GetMapping("/change")
	public String mypageChange(
			@RequestParam(value="start", required=false) String date1,
			@RequestParam(value="end", required=false) String date2,
			Model model, 
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Integer> statusArrayList = new ArrayList<Integer>();
		statusArrayList.add(5);
		statusArrayList.add(6);
		statusArrayList.add(7);
		statusArrayList.add(8);	
		
		if(date1 == null && date2 == null) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE,-7);
			Date d = new Date();
			String weekago = formatDate.format(cal.getTime());
			String today = formatDate.format(d);
			
			List<Order> order = orderService.findByUserNoOrder(user.getNo(), statusArrayList);
			model.addAttribute("date1", weekago);
			model.addAttribute("date2", today);
			model.addAttribute("order", order);
		} else {
			List<Order> order = orderService.findByUserNoAndStatusAndDateBetween(user.getNo(), date1, date2, statusArrayList);
			model.addAttribute("date1", date1);
			model.addAttribute("date2", date2);
			model.addAttribute("order", order);
		}
		
		return "home/user/mypage/change";
	}
	
	@GetMapping("/change/apply")
	public String mypageChangeApply(
			@RequestParam(value="start", required=false) String date1,
			@RequestParam(value="end", required=false) String date2,
			Model model, 
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Integer> statusArrayList = new ArrayList<Integer>();
		statusArrayList.add(4);
		
		if(date1 == null && date2 == null) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE,-7);
			Date d = new Date();
			String weekago = formatDate.format(cal.getTime());
			String today = formatDate.format(d);
			
			List<Order> order = orderService.findByUserNoOrder(user.getNo(), statusArrayList);
			model.addAttribute("date1", weekago);
			model.addAttribute("date2", today);
			model.addAttribute("order", order);
		} else {
			List<Order> order = orderService.findByUserNoAndStatusAndDateBetween(user.getNo(), date1, date2, statusArrayList);
			model.addAttribute("date1", date1);
			model.addAttribute("date2", date2);
			model.addAttribute("order", order);
		}
		
		return "home/user/mypage/changeApply";
	}
	
	@GetMapping("/exchange/apply")
	public String mypageExchangeApply(
			@RequestParam(value="no", required=false) long no,
			@RequestParam(value="type", required=false) int type,
			Model model, 
			HttpSession session) {
		Order order = orderService.findByNo(no);
		order.setStatus(type);
		orderService.updateOrder(order);
		return "redirect:/mypage/change";
	}
	
	@GetMapping("/myaccount")
	public String mypageMyaccount(
			Model model, 
			HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		User user = userService.getUserByNo(sessionUser.getNo());
		
		model.addAttribute("user", user);
		return "home/user/mypage/myaccount";
	}
	
	@ResponseBody
	@PostMapping("/myaccount/update")
	public void mypageMyaccountUpdate(
			@RequestParam(value="no") long no,
			@RequestParam(value="name") String name,
			@RequestParam(value="email") String email,
			@RequestParam(value="phone") String phone,
			@RequestParam(value="password", required=false) String password,
			Model model
		) {
		User user = userService.getUserByNo(no);
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		if (password != "") {
			user.setPassword(password);	
		}
		userService.updateUser(user);
	}
	
	@GetMapping("/withdraw")
	public String mypageWithdraw() {
		return "home/user/mypage/withdraw";
	}
	
	@GetMapping("/withdraw/complete")
	public String mypageWithdrawComplete() {
		return "home/main/withdrawComplete";
	}
	
	@ResponseBody
	@PostMapping("/withdraw")
	public int mypageWithdrawAjax(
			@RequestParam(value="id") String id,
			@RequestParam(value="password") String password,
			Model model,
			HttpSession session
		) {
		User user = (User) session.getAttribute("user");
		String sessionId = user.getId();
		String sessionPw = user.getPassword();
		if(sessionId.equals(id) && sessionPw.equals(password)) {
			userService.deleteUser(user);
			session.removeAttribute("user");
			return 0;
		} else {
			return 1;
		} 
	}
	
	@GetMapping("/address")
	public String mypageAddress(
			HttpSession session,
			Model model) {
		User user = (User) session.getAttribute("user");
		List<UserAddress> address = useraddressService.getAddressByUserNo(user.getNo());
		
		model.addAttribute("address", address);
		return "home/user/mypage/address";
	}
	
	@ResponseBody
	@PostMapping("/checkAddress")
	public String checkAddress(
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<UserAddress> address = useraddressService.getAddressByUserNo(user.getNo());
		if(address.size() >= 5) {
			return "many";
		} else {
			return "success";
		}
	}
	
	@ResponseBody
	@PostMapping("/address/basic/update")
	public void mypageAddressBasic(
			@RequestParam(value="no") long no,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		UserAddress original = useraddressService.getAddressByUserNoAndBasic(user.getNo(), 0);
		original.setBasic(1);
		useraddressService.updateUserAddress(original);
		
		UserAddress address = useraddressService.getAddressByNo(no);
		address.setBasic(0);
		useraddressService.updateUserAddress(address);
	}
	
	@ResponseBody
	@PostMapping("/address/del")
	public void mypageAddressDelete(
			@RequestParam(value="no") long no,
			HttpSession session) {
		UserAddress address = useraddressService.getAddressByNo(no);
		useraddressService.deleteUserAddress(address);
	}
	
	@ResponseBody
	@PostMapping("/address/add")
	public void mypageAddressAdd(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "zipcode") String zipcode,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "detailaddress") String detailaddress,
			@RequestParam(value = "reference") String reference,
			HttpSession session
			) {
		User user = (User) session.getAttribute("user");
		UserAddress userAddress = new UserAddress(user, name, phone, zipcode, address, detailaddress, reference, 1);
		useraddressService.saveUserAddress(userAddress);
	}
	
	@ResponseBody
	@PostMapping("/address/update")
	public void mypageAddressUpdate(
			@RequestParam(value = "no") long no,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "zipcode") String zipcode,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "detailaddress") String detailaddress,
			@RequestParam(value = "reference") String reference,
			HttpSession session
			) {
		UserAddress userAddress = useraddressService.getAddressByNo(no);
		userAddress.setName(name);
		userAddress.setPhone(phone);
		userAddress.setZipcode(zipcode);
		userAddress.setAddress(address);
		userAddress.setDetailaddress(detailaddress);
		userAddress.setReference(reference);
		
		useraddressService.updateUserAddress(userAddress);
	}
	
	@GetMapping("/myquestion")
	public String mypageMyQuestion(
			Model model, 
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<ProductQuestion> question = questionService.findByUserNo(user.getNo());
		
		model.addAttribute("question", question);
		return "home/user/mypage/myquestion";
	}
	
	@GetMapping("/review/write")
	public String mypageReviewWrite(
			@RequestParam(value="no", required=false) Long no,
			Model model, 
			HttpSession session,
			HttpServletResponse response) throws IOException {
		User user = (User) session.getAttribute("user");
		
		if(no == null) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE, -31);
			Date d = new Date();
			String monthago = formatDate.format(cal.getTime());
			String today = formatDate.format(d);
			
			List<OrderInfo> info = orderinfoService.findByUserNoandStatusAndDateLeftJoin(monthago, today, user.getNo());
	
			model.addAttribute("info", info);
			return "home/user/mypage/reviewWriteList";
		} else {
			File autoFolder = new File(autoFolderStatic + "/" +  t1.format(d));
			File autoFolder2 = new File(autoFolderStatic2 + "/" +  t1.format(d));
			if(osName.matches(".*Windows.*")) {
				if(!autoFolder.exists()) {
					autoFolder.mkdirs();
				}
			} else {
				if(!autoFolder2.exists()) {
					autoFolder2.mkdirs();
				}
			}
			
			OrderInfo info = orderinfoService.findByNo(no);
			List<ProductReview> review = reviewService.findByInfoNo(no);
			
			if(review.isEmpty() == false || info.getStatus() == 1) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				  
				out.println("<script>alert('이미 등록된 상품평이 있거나 삭제된 이력이 존재합니다.');location.href='/mypage/review/write';</script>");
				out.flush();
				 
			  return null;
			} else {
				model.addAttribute("info", info);
				return "home/user/mypage/reviewWrite";
			}
		}
	}
	
	@PostMapping(value="/review/add", consumes = "multipart/form-data")
	public String mypageReviewAdd(
			Long userno,
			Long productno,
			Long colorno,
			Long sizeno,
			String contents,
			Long infono,
			long score,
			@RequestParam("pic1") MultipartFile pic1,
			@RequestParam("pic2") MultipartFile pic2,
			@RequestParam("pic3") MultipartFile pic3,
			Model model, 
			HttpSession session) throws Exception {
		if (!pic1.isEmpty()) {
			int idx1 = pic1.getContentType().indexOf("/");
			int idxO1 = pic1.getOriginalFilename().indexOf(".");
			String pic1Ex = pic1.getContentType().substring(idx1+1);
			String pic1Bf = pic1.getOriginalFilename().substring(0, idxO1);

			newname = pic1Bf + System.currentTimeMillis() + "." + pic1Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname);
				FileCopyUtils.copy(pic1.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname);
				FileCopyUtils.copy(pic1.getBytes(), file);
			}
		} else {
			newname = null;
		}
		
		if (!pic2.isEmpty()) {
			int idx2 = pic2.getContentType().indexOf("/");
			int idxO2 = pic2.getOriginalFilename().indexOf(".");
			String pic2Ex = pic2.getContentType().substring(idx2+1);
			String pic2Bf = pic2.getOriginalFilename().substring(0, idxO2);
			
			newname2 = pic2Bf + System.currentTimeMillis() + "." + pic2Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname2);
				FileCopyUtils.copy(pic2.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname2);
				FileCopyUtils.copy(pic2.getBytes(), file);
			}
		} else {
			newname2 = null;
		}
		
		if (!pic3.isEmpty()) {
			int idx3 = pic3.getContentType().indexOf("/");
			int idxO3 = pic3.getOriginalFilename().indexOf(".");
			String pic3Ex = pic3.getContentType().substring(idx3+1);
			String pic3Bf = pic3.getOriginalFilename().substring(0, idxO3);
			
			newname3 = pic3Bf + System.currentTimeMillis() + "." + pic3Ex;
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname3);
				FileCopyUtils.copy(pic3.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname3);
				FileCopyUtils.copy(pic3.getBytes(), file);
			}
		} else {
			newname3 = null;
		}
		
		Product product = productService.getProductById(productno);
		User user = userService.getUserByNo(userno);
		Color color = colorService.getColorByNo(colorno);
		ClothesSize size = sizeService.getClothesSizeByNo(sizeno);
		OrderInfo info = orderinfoService.findByNo(infono);
		
		info.setStatus(1);
		orderinfoService.updateOrderInfo(info);
		
		int originalPoint = user.getPoint();
		int updatePoint;

		Date day = new Date();
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		contents = contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		if (newname == null && newname2 == null && newname3 == null) {
			ProductReview review = new ProductReview((int)score, product, user, size, color, contents, info, newname, newname2, newname3, today.format(day), 0);
			updatePoint = originalPoint + 100;
			user.setPoint(updatePoint);
			userService.updateUser(user);
			reviewService.saveProductReview(review);
		} else {
			updatePoint = originalPoint + 500;
			user.setPoint(updatePoint);
			userService.updateUser(user);
			ProductReview review = new ProductReview((int)score, product, user, size, color, contents, info, newname, newname2, newname3, today.format(day), 1);
			reviewService.saveProductReview(review);
		}
		
		return "redirect:/mypage/review";
	}
	
	@GetMapping("/review")
	public String mypageMyReview(
			Model model, 
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<ProductQuestion> question = questionService.findByUserNo(user.getNo());
		
		model.addAttribute("question", question);
		return "home/user/mypage/myquestion";
	}
}
