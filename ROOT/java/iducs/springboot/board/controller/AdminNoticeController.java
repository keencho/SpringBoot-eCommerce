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

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.service.NoticeService;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {
	@Autowired
	NoticeService noticeService;
	
	String osName = System.getProperty("os.name");
	static File cwd = new File("src/main/resources/static/uploads/notice/");			// 윈도우 업로드 경로
	static File cwd2 = new File("webapps/ROOT/WEB-INF/classes/static/uploads/notice/");	// 리눅스 실제 웹서버 업로드 경로
	static File path = cwd.getAbsoluteFile();
	static File path2 = cwd2.getAbsoluteFile();
	static String autoFolderStatic = path.toString();
	static String autoFolderStatic2 = path2.toString();
	
	Date d = new Date();
	SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
	String attachName = null;
	String attachNameOriginal = null;

	@GetMapping("")
	public String noticeHome(Model model) {
		List<Notice> notice = noticeService.findAll();
		model.addAttribute("notice", notice);
		
		return "admin/notice/list";
	}
	
	@GetMapping("/add")
	public String noticeAddForm(Model model) {
		return "admin/notice/add";
	}
	
	@PostMapping(value="/add", consumes = "multipart/form-data")
	public String noticeAdd(
			@RequestParam("title") String title,
			@RequestParam("contents") String contents,
			@RequestParam("attach") MultipartFile attach,
			Model model
			) throws Exception{
		File autoFolder = new File(autoFolderStatic + "/" +  t1.format(d));		// 파일이 업로드될 경로가 존재하지 않으면 생성하는 소스
		File autoFolder2 = new File(autoFolderStatic2 + "/" +  t1.format(d));	// 폴더에는 최대 저장될수 있는 개수가 존재하기 때문에 업로드 당시 날짜를 기준으로 폴더명 지정
		if(osName.matches(".*Windows.*")) {
			if(!autoFolder.exists()) {
				autoFolder.mkdirs();
			}
		} else {
			if(!autoFolder2.exists()) {
				autoFolder2.mkdirs();
			}
		}
		
		if(!attach.isEmpty()) {
			int idx1 = attach.getContentType().indexOf("/");					// 업로드할 파일의 형식중 application/의 위치를 검색
			int idx2 = attach.getOriginalFilename().indexOf(".");				// 업로드할 파일의 오리지널 이름에서 .의 위치를 저장
			String attach1 = attach.getContentType().substring(idx1 + 1);		// 업로드할 파일의 형식(사진, 파일 등) 을 저장(application/ 후에 나오는 형식)
			// getContentType의 경우 이미지 파일들은 관련없지만 txt, office excel, word등의 파일은 MIME 형식으로 출력되므로 주의 필요(다운로드엔 문제없음, 사용자에게 보여지는 문장을 위해 필요함)
			String attach2 = attach.getOriginalFilename().substring(0, idx2);	// 업로드할 파일의 원본 이름을 저장
			
			attachName = attach2 + System.currentTimeMillis() + "." + attach1;	// db에 올라갈 첨부파일명은 오리지널이름 + 현재 시간 + . + 파일 형식
			attachNameOriginal = attach.getOriginalFilename();					// 사용자에게 보여질 파일명
			if(osName.matches(".*Windows.*")) {
				File file = new File(path + "/" + t1.format(d), attachName);	// 현재 jvm이 구동되는 환경의 os가 윈도우일 경우 업로드 경로 지정, path의 경우 현재 controller 상단의 전역변수로 선언되어있음
				FileCopyUtils.copy(attach.getBytes(), file);					// 이부분이 실제 파일이 업로드되는 부분, FileCopyUtils 메소드를 이용해 byte타입배열을 지정한 File(경로)에 복사함
			} else {															// 구동 환경의 os가 리눅스일경우(나머지 os는 고려하지 않았음)
				File file = new File(path2 + "/" + t1.format(d), attachName);
				FileCopyUtils.copy(attach.getBytes(), file);
			}
		} else {
			attachName = null;
			attachNameOriginal = null;
			// 이 null처리를 해주지 않을 경우 --> 만약 바로 전 게시글에는 첨부파일이 존재하고, 현재 등록할 게시글에는 첨부파일이 존재하지 않는다고 치면 현재 등록할 게시글에 이전 게시글의 첨부파일이 등록되는 현상이 있음.
		}
		contents = contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		Notice notice = new Notice(title, contents, t1.format(d), 0, attachName, attachNameOriginal);
		noticeService.saveNotice(notice);
		
		return "redirect:/admin/notice";
	}
	
	@GetMapping("/view/{no}")
	public String noticeView(
			@PathVariable("no") Long no,
			Model model
			) {
		Notice notice = noticeService.findByNo(no);
		model.addAttribute("notice", notice);
		return "admin/notice/view";
	}
	
	@GetMapping("/download")
	public void noticeDownload(
			@RequestParam(value = "no", required = true) long no,
			HttpServletResponse response,
			HttpServletRequest request
			) throws Exception{
		Notice notice = noticeService.findByNo(no);
		File file;
		if(osName.matches(".*Windows.*")) {
			file = new File(cwd + "/" + notice.getDate(), notice.getAttach());
		} else {
			file = new File(cwd2 + "/" + notice.getDate(), notice.getAttach());
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
	
	@GetMapping("/update/{no}")
	public String noticeUpdateForm(
			@PathVariable("no") long no,
			Model model) {
		Notice notice = noticeService.findByNo(no);
		model.addAttribute("notice", notice);
		return "admin/notice/update";
	}
	
	@DeleteMapping("/del/{no}")
	public String noticeDel(
			@PathVariable("no") Long no,
			Model model
			) {
		Notice notice = noticeService.findByNo(no);

		if(notice.getAttach() != null) {			// 삭제할 공지사항에 첨부파일이 존재할시 삭제
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + notice.getDate(), notice.getAttach());
			} else {
				file = new File(cwd2 + "/" + notice.getDate(), notice.getAttach());
			}
			file.delete();
		}

		noticeService.deleteNotice(notice);
		return "redirect:/admin/notice";
	}
	
	@PutMapping("/update/{no}")
	public String noticeUpdate(
			@PathVariable("no") long no,
			@RequestParam("title") String title,
			@RequestParam("contents") String contents,
			@RequestParam("attach_status") int status,
			@RequestParam("attach") MultipartFile attach,
			Model model
			) throws Exception{
		Notice notice = noticeService.findByNo(no);
		notice.setTitle(title);
		contents = contents.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		notice.setContents(contents);
		
		if(status == 0) {								// 첨부파일이 기존에 없었거나 삭제되지 않았을 경우
			if(!attach.isEmpty()) {						// 첨부파일을 새로 등록했을때
				int idx1 = attach.getContentType().indexOf("/");
				int idx2 = attach.getOriginalFilename().indexOf(".");
				String attach1 = attach.getContentType().substring(idx1 + 1);
				String attach2 = attach.getOriginalFilename().substring(0, idx2);
				
				attachName = attach2 + System.currentTimeMillis() + "." + attach1;
				attachNameOriginal = attach.getOriginalFilename();
				try {
					if(!notice.getAttach().isEmpty()) {	// 기존의 첨부파일이 있다면
						File file;
						if(osName.matches(".*Windows.*")) {
							file = new File(cwd + "/" + notice.getDate(), notice.getAttach());
						} else {
							file = new File(cwd2 + "/" + notice.getDate(), notice.getAttach());
						}
						file.delete(); 					// 기존의 첨부파일은 삭제
					}
				} catch (Exception e) {}
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + notice.getDate(), attachName);
					FileCopyUtils.copy(attach.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + notice.getDate(), attachName);
					FileCopyUtils.copy(attach.getBytes(), newFile);
				}
				notice.setAttach(attachName);
				notice.setAttach_original(attachNameOriginal);
			}
		} else if (status == 1) {	// 기존의 첨부파일을 삭제하고자 할 경우
			File file;
			if(osName.matches(".*Windows.*")) {
				file = new File(cwd + "/" + notice.getDate(), notice.getAttach());
			} else {
				file = new File(cwd2 + "/" + notice.getDate(), notice.getAttach());
			}
			file.delete();
			attachName = null;
			attachNameOriginal = null;
			if(!attach.isEmpty()) {
				int idx1 = attach.getContentType().indexOf("/");
				int idx2 = attach.getOriginalFilename().indexOf(".");
				String attach1 = attach.getContentType().substring(idx1 + 1);
				String attach2 = attach.getOriginalFilename().substring(0, idx2);
				
				attachName = attach2 + System.currentTimeMillis() + "." + attach1;
				attachNameOriginal = attach.getOriginalFilename();
				
				if(osName.matches(".*Windows.*")) {
					File newFile = new File(path + "/" + notice.getDate(), attachName);
					FileCopyUtils.copy(attach.getBytes(), newFile);
				} else {
					File newFile = new File(path2 + "/" + notice.getDate(), attachName);
					FileCopyUtils.copy(attach.getBytes(), newFile);
				}
			}
			notice.setAttach(attachName);
			notice.setAttach_original(attachNameOriginal);
		}
		
		noticeService.updateNotice(notice);
		return "redirect:/admin/notice/view/" + no;
	}
	
}
