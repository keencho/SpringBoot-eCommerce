package iducs.springboot.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
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
			@RequestParam(value="date1", required=false) String date1,
			@RequestParam(value="date2", required=false) String date2,
			@RequestParam(value="type", required=false) String type,
			Model model) {
		if(date1 == null && date2 == null && type == null) {
			List<Consulting> consulting = consultingService.findAll();
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			cal.add(Calendar.DATE,-7);
			Date d = new Date();
			String weekago = formatDate.format(cal.getTime());
			String today = formatDate.format(d);
			
			model.addAttribute("date1", weekago);
			model.addAttribute("date2", today);
			model.addAttribute("consulting", consulting);
		} else {
			if (type.equals("all")) {
				List<Consulting> consulting = consultingService.findByDateBetween(date1, date2);
				model.addAttribute("consulting", consulting);
			} else {
				List<Consulting> consulting = consultingService.findByTypeAndDateBetween(type, date1, date2);	
				model.addAttribute("consulting", consulting);
			}
			model.addAttribute("type", type);
			model.addAttribute("date1", date1);
			model.addAttribute("date2", date2);
		}
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
	
	@PutMapping("/answer/{no}")
	public String adminConsultingAnswer(
			@PathVariable("no") Long no,
			@RequestParam("answer") String answer,
			Model model
			) throws UnsupportedEncodingException, MessagingException {
		Date d = new Date();
		SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
		answer = answer.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		Consulting consulting = consultingService.findByNo(no);
		this.sendEmail(consulting.getEmail(), consulting.getUser().getName(), consulting.getTitle());
		
		consulting.setAnswer(answer);
		consulting.setDate_a(t1.format(d));
		consulting.setStatus(1);
		
		consultingService.updateConsulting(consulting);
		
		return "redirect:/admin/consulting";
	}
	
	public void sendEmail(String email, String name, String title) throws UnsupportedEncodingException, MessagingException {	// 이메일 보내기
		final String user = "seyoung12314@gmail.com";
		final String password = "sw971312!@";
		String content = "keencho 가상 쇼핑몰입니다.<br><br>고객님의 <font color='blue'>'" + title + "'</font> 1:1문의에 대한 답변이 등록되었습니다.<br><u><a href='http://keencho.ml:8900/'>여기</a></u>를 눌러 지금 바로 답변을 확인해보세요!";
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));		// 수신자 이메일 주소
			
			message.setSubject(name + "님의 1:1 문의에 대한 답변이 등록되었습니다!");
			message.setText(content, "UTF-8", "html");
			
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
