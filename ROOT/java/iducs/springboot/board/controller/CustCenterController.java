package iducs.springboot.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import iducs.springboot.board.domain.Consulting;
import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.domain.Order;
import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.NoticeEntity;
import iducs.springboot.board.service.ConsultingService;
import iducs.springboot.board.service.NoticeService;
import iducs.springboot.board.service.OrderInfoService;
import iducs.springboot.board.service.OrderService;


@Controller
@RequestMapping("/custcenter")
public class CustCenterController {
	@Autowired NoticeService noticeService;
	@Autowired OrderService orderService;
	@Autowired OrderInfoService orderinfoService;
	@Autowired ConsultingService consultingService;
	
	String osName = System.getProperty("os.name");
	static File cwd = new File("src/main/resources/static/uploads/consulting");	// 윈도우 업로드 경로
	static File cwd2 = new File("webapps/ROOT/WEB-INF/classes/static/uploads/consulting");	// 리눅스 실제 웹서버 업로드 경로
	static File cwd_notice = new File("src/main/resources/static/uploads/notice");
	static File cwd_notice2 = new File("webapps/ROOT/WEB-INF/classes/static/uploads/notice");
	static File path = cwd.getAbsoluteFile();
	static File path2 = cwd2.getAbsoluteFile();
	static String autoFolderStatic = path.toString();
	static String autoFolderStatic2 = path2.toString();
	
	Date d = new Date();
	SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
	
	String newname = null;
	String newname_original = null;
	
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
	public String custcenterNonUserTracking(
			HttpSession session) {
		if (session.getAttribute("user") != null)
			return "redirect:/404";
		
		return "home/custcenter/tracking";
	}
	
	@PostMapping("/tracking/find")
	public String custcenterNonUserTrackingFind(
			@RequestParam(value="orderno", required=true) String orderno,
			@RequestParam(value="password", required=true) String password,
			Model model,
			HttpServletResponse response
			) throws IOException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.MONTH, -3);
		String threeMonthAgo = formatDate.format(cal.getTime());
		try {
			Order order = orderService.findByOrdernoAndPassword(orderno, password);
			int dateResult = threeMonthAgo.compareTo(order.getDate());		// 날짜비교 --> 앞의 변수가 크면 1, 작으면 -1, 같으면 0
			if(dateResult == 1) {		// 주문일이 오늘날 -3달한 결과값보다 작으면 조회를 할수없음.
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('조회가능한 기간이 지났습니다.'); self.close();</script>");
				out.flush();
			}
			order.getDate();
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
	
	@GetMapping("/counsel")
	public String custcenterCounseling (
			HttpSession session,
			Model model) {
		if (session.getAttribute("user") == null)
			return "redirect:/404";
		
		User user = (User) session.getAttribute("user");
		String mail = user.getEmail();
		int idx = mail.indexOf("@");
		user.setMail1(mail.substring(0, idx));
		user.setMail2(mail.substring(idx+1));
		
		model.addAttribute("user", user);
		
		return "home/custcenter/counsel";
		
	}
	
	@PostMapping("/counsel/add")
	public String custcenterCounselingAdd(
			@RequestParam("mail1") String mail1,
			@RequestParam("mail2") String mail2,
			@RequestParam("type") String type,
			@RequestParam("title") String title,
			@RequestParam("contents") String contents,
			@RequestParam("attach") MultipartFile attach,
			HttpSession session,
			Model model) throws IOException {
		User user = (User) session.getAttribute("user");
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
		
		if (!attach.isEmpty()) {
			int idx1 = attach.getContentType().indexOf("/");
			int idxO1 = attach.getOriginalFilename().indexOf(".");
			String attachEx = attach.getContentType().substring(idx1+1);
			String attachBf = attach.getOriginalFilename().substring(0, idxO1);

			newname = attachBf + System.currentTimeMillis() + "." + attachEx;
			newname_original = attach.getOriginalFilename();
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), newname);
				FileCopyUtils.copy(attach.getBytes(), file);
			} else {
				File file = new File(path2 + "/" + t1.format(d), newname);
				FileCopyUtils.copy(attach.getBytes(), file);
			}
		} else {
			newname = null;
			newname_original = null;
		}
		
		contents = contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		Consulting consulting = new Consulting(user, mail1 + "@" + mail2, title, contents, null, t1.format(d), null, newname, 0, type, newname_original);
		consultingService.saveConsulting(consulting);
		return "redirect:/mypage/consulting";
	}
	
	@DeleteMapping("/consulting/del/{no}")
	public String custcenterConsultingDel(
			@PathVariable("no") Long no
			) {
		Consulting consulting = consultingService.findByNo(no);
		consultingService.deleteConsulting(consulting);
		
		return "redirect:/mypage/consulting";
	}
	
	@GetMapping("/consulting/view")
	public String custcenterConsultingView(
			@RequestParam(value="no", required=true) Long no,
			Model model
			) {
		
		Consulting consulting = consultingService.findByNo(no);
		model.addAttribute("consulting", consulting);
		
		return "home/custcenter/consultingView";
		
	}
	
	@GetMapping("/notice/download")
	public void noticeDownload(
			@RequestParam(value = "no", required = true) long no,
			HttpServletResponse response,
			HttpServletRequest request
			) throws Exception{
		Notice notice = noticeService.findByNo(no);
		File file;
		if(osName.matches(".*Windows.*")) {
			file = new File(cwd_notice + "/" + notice.getDate(), notice.getAttach());
		} else {
			file = new File(cwd_notice2 + "/" + notice.getDate(), notice.getAttach());
		}
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		String header = request.getHeader("User-Agent");
		String fileName;

		 if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
	        //인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩 
	        fileName = URLEncoder.encode(notice.getAttach_original(), "UTF-8");
		 } else {
	        //나머지 브라우저에서 인코딩
	        fileName = new String(notice.getAttach_original().getBytes("UTF-8"), "iso-8859-1");
	    }
		 
		//형식을 모르는 파일첨부용 contentType
	    response.setContentType("application/octet-stream");

	    //다운로드와 다운로드될 파일이름
	    response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
		FileCopyUtils.copy(in, response.getOutputStream());
	    in.close();
	    response.getOutputStream().flush();
	    response.getOutputStream().close();

	}
}
