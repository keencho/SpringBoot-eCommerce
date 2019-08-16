package iducs.springboot.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;

import iducs.springboot.board.domain.Product;
import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.ProductQuestionEntity;
import iducs.springboot.board.service.ProductQuestionService;
import iducs.springboot.board.service.ProductService;
import iducs.springboot.board.service.UserService;


@Controller
@RequestMapping("/product/communication")
public class ProductCommunicationController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductQuestionService productquestionService;
	@Autowired
	UserService userService;
	
	@ResponseBody
	@PostMapping("/question/add")
	public void questionAdd(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "contents") String contents,
			@RequestParam(value = "product_no") String s_product_no,
			@RequestParam(value = "type") String s_type,
			HttpSession session) {
		int personal_no = 1; // 상품별 문의의 '개별번호'의 등록을 위한 변수선언 (primary key가 아님)
		int type = Integer.parseInt(s_type); // 상품문의의 유형
		User user = (User) session.getAttribute("user");
		Date d = new Date();
		SimpleDateFormat todayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String today = todayFormat.format(d); // 문의 등록일
		long product_no = Long.parseLong(s_product_no);
		Product product = productService.getProductById(product_no);
		List<ProductQuestion> productcheck = productquestionService.getProductQuestionOriginal(product_no);
		for(int i = 0; i < productcheck.size(); i ++) { // 상품별 문의 개별번호를 확인해 이미 존재하는 번호이면 +1처리
			if(personal_no == productcheck.get(i).getPersonal_no()) {
				personal_no += 1;
			}
		}
		ProductQuestion productquestion = new ProductQuestion(product, personal_no, type, user, name, contents, 0, today, null);
		productquestionService.saveProductQuestion(productquestion);
	}
	
	@GetMapping("/question/list/{productno}/{sort}")
	public String questionSortList(	// 상품별 문의에 전체 / 답변완료 sorting
			@PathVariable(value = "productno") long product_no,
			@PathVariable(value = "sort") int sort,
			@PageableDefault(size = 5, sort = "no", direction = Sort.Direction.DESC) Pageable questionPageable,
			Model model,
			HttpServletRequest request
			) throws Exception {
		if (sort == 0) {
			List<ProductQuestion> productQuestion = productquestionService.getProductQuestion(product_no, questionPageable);
			Page<ProductQuestionEntity> questionPage = productquestionService.getProductQuestionPage(questionPageable, product_no);
			for(int i=0; i < productQuestion.size(); i++) {
				String id = productQuestion.get(i).getUser_no().getId(); 
				String idSubstring = id.substring(0, 4);
				int length = id.length() - idSubstring.length();
				String replace = new String(new char[length]).replace("\0", "*");
				productQuestion.get(i).getUser_no().setId(idSubstring + replace);
			}
			model.addAttribute("productquestion", productQuestion);
			model.addAttribute("questionPage", questionPage);
		} else if (sort == 1) {
			List<ProductQuestion> productQuestion = productquestionService.getProductQuestionStatus(product_no, sort, questionPageable);
			Page<ProductQuestionEntity> questionPage = productquestionService.getProductQuestionStatusPage(questionPageable, product_no, sort);
			for(int i=0; i < productQuestion.size(); i++) {
				String id = productQuestion.get(i).getUser_no().getId(); 
				String idSubstring = id.substring(0, 4);
				int length = id.length() - idSubstring.length();
				String replace = new String(new char[length]).replace("\0", "*");
				productQuestion.get(i).getUser_no().setId(idSubstring + replace);
			}
			model.addAttribute("productquestion", productQuestion);
			model.addAttribute("questionPage", questionPage);
		}

		return "/home/product/question";
	}
}
