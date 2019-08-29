package iducs.springboot.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.domain.Order;
import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.entity.NoticeEntity;
import iducs.springboot.board.service.NoticeService;
import iducs.springboot.board.service.OrderInfoService;
import iducs.springboot.board.service.OrderService;


@Controller
@RequestMapping("/custcenter")
public class CustCenterController {
	@Autowired NoticeService noticeService;
	@Autowired OrderService orderService;
	@Autowired OrderInfoService orderinfoService;
	
	@GetMapping("/faq")
	public String custcenterFaq(
			Model model, 
			HttpSession session) {
		return "home/custcenter/faq";
	}
	
	@GetMapping("/notice")
	public String custcenterNoticeBoard(
			@PageableDefault(size = 3, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value="search", required=false) String search,
			@RequestParam(value="category", required=false) Long category,
			Model model, 
			HttpSession session) {
		if(search == null && category == null) {
			List<Notice> notice = noticeService.findEverything(pageable);
			Page<NoticeEntity> noticePage = noticeService.findAll(pageable);
			
			model.addAttribute("notice", notice);
			model.addAttribute("page", noticePage);
		} else if (search != null && category != null) {
			if (category == 0) {	// 제목으로검색
				List<Notice> notice = noticeService.findBytitle(pageable, search);
				Page<NoticeEntity> noticePage = noticeService.findByTitle(search, pageable);
				
				model.addAttribute("notice", notice);
				model.addAttribute("page", noticePage);
			} else if (category == 1) {		// 내용으로 검색
				List<Notice> notice = noticeService.findByContents(pageable, search);
				Page<NoticeEntity> noticePage = noticeService.findByContents(search, pageable);
				
				model.addAttribute("notice", notice);
				model.addAttribute("page", noticePage);
			} else if (category == 2) {		// 제목 + 내용으로 검색
				List<Notice> notice = noticeService.findByTitleOrContents(pageable, search, search);
				Page<NoticeEntity> noticePage = noticeService.findByTitleOrContents(search, search, pageable);
				
				model.addAttribute("notice", notice);
				model.addAttribute("page", noticePage);
			}
		}
		return "home/custcenter/noticeList";
	}
	
	@GetMapping("/notice/{no}")
	public String custcenterNoticeView(
			@PathVariable("no") Long no,
			Model model,
			HttpServletResponse response,
			HttpServletRequest request) {
		Notice notice = noticeService.findByNo(no);
		
		// 게시글 중복조회를 막기위해 일단 저장된 쿠키를 불러온다.
		Cookie[] findCookie = request.getCookies();
		Map<String, String> map = new HashMap<String, String>();
		if(request.getCookies() != null) {	// 쿠기값이 있으면
			for (int i = 0 ; i < findCookie.length; i ++) {
				Cookie obj = findCookie[i];
				map.put(obj.getName(), obj.getValue());	// map 객체에 현재 쿠키에 저장되어있는 값을 넣는다.
			}
		}
		
		// map객체에서 'read_count'의 값을 불러온다.
		String readCount = (String) map.get("notice_count");
		// 새로 저장될 쿠기값은 마지막 쿠기값에서 | + 게시글 번호로 정해진다.
		String newReadCount = "|" + no;
		
		// map 객체에서 'reac_count'의 값을 불러와 저장한 readCount변수에, 현재 페이지 공지사항 번호가 존재하는가?
		// indexOfIgnoreCase ---> apache.commons에 있는 객체, 문자열중 찾을 문자열이 포함된 첫번쨰 인덱스를 반환한다. 문자열이 없다면 -1을 반환한다.
		if(StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1) {
			// 없을 경우 새로운 쿠키를 생성한다.
			Cookie cookie = new Cookie("notice_count", readCount + newReadCount);
			cookie.setMaxAge(60*60*6); // 쿠키의 최대유지시간은 6시간
			response.addCookie(cookie);
			notice.setViews(notice.getViews() + 1);
			noticeService.updateNotice(notice);
		}

		model.addAttribute("notice", notice);
		
		return "home/custcenter/noticeView";
		
	}
	
	@GetMapping("/tracking")
	public String custcenterNonUserTracking() {
		return "home/custcenter/tracking";
	}
	
	@PostMapping("/tracking/find")
	public String custcenterNonUserTrackingFind(
			@RequestParam(value="orderno", required=true) String orderno,
			@RequestParam(value="password", required=true) String password,
			Model model,
			HttpServletResponse response
			) throws IOException {
		try {
			Order order = orderService.findByOrdernoAndPassword(orderno, password);
			List<OrderInfo> orderInfo = orderinfoService.findByOrderNo(order.getNo());
			for (int i = 0; i < orderInfo.size(); i++) {
				orderInfo.get(i).setInt_price(Integer.parseInt(orderInfo.get(i).getPrice()));
			}
			model.addAttribute("order", order);
			model.addAttribute("info", orderInfo);
		} catch (Exception e) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 정보를 입력하셨습니다.'); self.close();</script>");
			out.flush();
		}
		
		return "/home/user/mypage/orderinfo";
	}
}
