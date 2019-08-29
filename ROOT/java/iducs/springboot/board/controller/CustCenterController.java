package iducs.springboot.board.controller;

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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.entity.NoticeEntity;
import iducs.springboot.board.service.NoticeService;


@Controller
@RequestMapping("/custcenter")
public class CustCenterController {
	@Autowired NoticeService noticeService;
	
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
		
		// 게시글 중복주회를 막기위해 일단 저장된 쿠키를 불러온다.
		Cookie[] findCookie = request.getCookies();
		Map<String, String> map = new HashMap<String, String>();
		if(request.getCookies() != null) {
			for (int i = 0 ; i < findCookie.length; i ++) {
				Cookie obj = findCookie[i];
				map.put(obj.getName(), obj.getValue());
			}
		}
		
		// 저장된 쿠키중 'read_count'의 값을 불러온다.
		String readCount = (String) map.get("read_count");
		// 새로 저장될 쿠기값은 마지막 쿠기값에서 | + 게시글 번호로 정해진다.
		String newReadCount = "|" + no;
		
		// 저장된 쿠키에 새로운 쿠키값이 존재하는가?
		if(StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1) {
			// 없을 경우 새로운 쿠키를 생성한다.
			Cookie cookie = new Cookie("read_count", readCount + newReadCount);
			cookie.setMaxAge(60*60*6); // 쿠키의 최대유지시간은 6시간
			response.addCookie(cookie);
			notice.setViews(notice.getViews() + 1);
			noticeService.updateNotice(notice);
		}

		model.addAttribute("notice", notice);
		
		return "home/custcenter/noticeView";
		
	}
}
