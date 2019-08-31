package iducs.springboot.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iducs.springboot.board.domain.Consulting;
import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.service.ConsultingService;
import iducs.springboot.board.service.UserService;

@Controller
@RequestMapping("/admin/consulting")
public class AdminConsultingController {
	@Autowired
	ConsultingService consultingService;
	@Autowired
	UserService userService;
	
	String osName = System.getProperty("os.name");
	static File cwd = new File("src/main/resources/static/uploads/consulting/");			// 윈도우 업로드 경로
	static File cwd2 = new File("webapps/ROOT/WEB-INF/classes/static/uploads/consulting/");	// 리눅스 실제 웹서버 업로드 경로
	static File path = cwd.getAbsoluteFile();
	static File path2 = cwd2.getAbsoluteFile();
	static String autoFolderStatic = path.toString();
	static String autoFolderStatic2 = path2.toString();
	
	Date d = new Date();
	SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
	String attachName = null;
	String attachNameOriginal = null;
	
	@GetMapping("")
	public String adminConsultingHome(
			Model model) {
		List<Consulting> consulting = consultingService.findAll();
		model.addAttribute("consulting", consulting);
		
		return "admin/consulting/list";
	}
	
	@GetMapping("/answer/{no}")
	public String adminConsultingAnswerForm(
			@PathVariable("no") Long no,
			Model model
			) {
		Consulting consulting = consultingService.findByNo(no);
		model.addAttribute("consulting", consulting);
		
		return "admin/consulting/answer";
	}
	
	@PutMapping("/answer/{no}")
	public String adminConsultingAnswer(
			@PathVariable("no") Long no,
			@RequestParam("answer") String answer,
			Model model
			) {
		Date d = new Date();
		SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
		answer = answer.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		Consulting consulting = consultingService.findByNo(no);
		consulting.setAnswer(answer);
		consulting.setDate_a(t1.format(d));
		consulting.setStatus(1);
		
		consultingService.updateConsulting(consulting);
		
		return "redirect:/admin/consulting";
	}
	
	@DeleteMapping("/del/{no}")
	public String adminConsultingDel(
			@PathVariable("no") Long no
			) {
		Consulting consulting = consultingService.findByNo(no);
		consultingService.deleteConsulting(consulting);
		
		return "redirect:/admin/consulting";
	}
	
	@GetMapping("/download")
	public void consultingDownload(
			@RequestParam(value = "no", required = true) long no,
			HttpServletResponse response,
			HttpServletRequest request
			) throws Exception{
		Consulting consulting = consultingService.findByNo(no);
		File file;
		if(osName.matches(".*Windows.*")) {
			file = new File(cwd + "/" + consulting.getDate_q(), consulting.getAttach());
		} else {
			file = new File(cwd2 + "/" + consulting.getDate_q(), consulting.getAttach());
		}
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		String header = request.getHeader("User-Agent");
		String fileName;
		
		 if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
	        //인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩 
	        fileName = URLEncoder.encode(consulting.getAttach_original(), "UTF-8");
		 } else {
	        //나머지 브라우저에서 인코딩
	        fileName = new String(consulting.getAttach_original().getBytes("UTF-8"), "iso-8859-1");
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
