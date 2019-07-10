package iducs.springboot.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions.CountFunction;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

import iducs.springboot.board.domain.Comment;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.QuestionEntity;
import iducs.springboot.board.exception.ResourceNotFoundException;
import iducs.springboot.board.repository.UserRepository;
import iducs.springboot.board.service.CommentService;
import iducs.springboot.board.service.QuestionService;
import iducs.springboot.board.service.UserService;
import iducs.springboot.board.utils.HttpSessionUtils;

@Controller
@RequestMapping("/questions")
public class QuerstionController {
	@Autowired QuestionService questionService; // 의존성 주입(Dependency Injection) :
	@Autowired CommentService commentService; // 의존성 주입(Dependency Injection) :
	/*
	@GetMapping("")
	public String redirect() {
		return "redirect:/questions/p/1"; 
	}*/
	
	@GetMapping("")
	public String getAllUser(Model model, HttpSession session, @PageableDefault(size=3, sort="id", direction = Sort.Direction.ASC) Pageable pageable) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login-form";
		}
		List<Question> questions = questionService.getQuestionsPage(pageable);
		Page<QuestionEntity> page = questionService.getQuestionsGetPage(pageable);
		model.addAttribute("questions", questions);
		model.addAttribute("page", page);
		model.addAttribute("countall",page.getTotalElements());
		return "/questions/list"; 
	}	
	@GetMapping("/test1")
	public String test() {		
		return "index";
	}
	@GetMapping("/{id}")
	public String getQuestionById(@PathVariable(value = "id") Long id, Model model) {
		Question question = questionService.getQuestionById(id);
		System.out.println(question.getAnswers().size());
		model.addAttribute("question", question);
		model.addAttribute("replycount", question.getAnswers().size());
		return "/questions/info";
	}
	@PostMapping("")
	// public String createUser(Question question, Model model, HttpSession session) {
	public String createUser(String title, String contents, Model model, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Question newQuestion = new Question(title, sessionUser, contents);		
		// Question newQuestion = new Question(question.getTitle(), writer, question.getContents());	
		questionService.saveQuestion(newQuestion);
		return "redirect:/questions"; // get 방식으로  리다이렉션 - Controller를 통해 접근
	}
	
	@GetMapping("/{id}/form")
	public String getUpdateForm(@PathVariable(value = "id") Long id, Model model) {
		Question question = questionService.getQuestionById(id);
		model.addAttribute("question", question);
		
		return "/questions/edit";
	}
	@GetMapping("/{no}/reply/{id}")
	public String getReplyUpdateForm(@PathVariable(value = "id") Long id, @PathVariable(value = "no")Long no, Model model) {
		Question question = questionService.getQuestionById(no);
		Comment comment = commentService.getCommentById(id);
		model.addAttribute("answer", comment);
		model.addAttribute("question", question);
		return "/questions/replyEdit";
	}
	@PutMapping("/{id}")
	public String updateQuestionById(@PathVariable(value = "id") Long id, @Valid Question formQuestion, String title, String contents, Model model) {
		Question question = questionService.getQuestionById(id);
		question.setTitle(formQuestion.getTitle());
		question.setContents(formQuestion.getContents());
		questionService.updateQuestion(question);		
		return "redirect:/questions/" + id;
	}
	@PutMapping("/{no}/answer/{id}")
	public String updateReplyById(@PathVariable(value = "id") Long id, @PathVariable(value = "no") Long no, @Valid Question formQuestion, String title, String contents, Model model) {
		Comment comment = commentService.getCommentById(id);
		comment.setContents(formQuestion.getContents());
		commentService.updateComment(comment);
		return "redirect:/questions/" + no;
	}
//	@DeleteMapping("/{id}")
//	public String deleteQuestionById(@PathVariable(value = "id") Long id, Model model) {
//		Question question = questionService.getQuestionById(id);
//		questionService.deleteQuestion(question);
//		model.addAttribute("userId", question.getWriter().getUserId());
//		return "redirect:/questions";
//	}
	@DeleteMapping("/{no}/answer/{id}")
	public String deleteReplyById(@PathVariable(value = "id") Long id, @PathVariable(value = "no") Long no, Model model) {
		Comment comment= commentService.getCommentById(id);
		commentService.deleteComment(comment);
		return "redirect:/questions/" + no;
	}
}
