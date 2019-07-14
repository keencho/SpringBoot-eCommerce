package iducs.springboot.board.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iducs.springboot.board.domain.Comment;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.service.CommentService;
import iducs.springboot.board.service.QuestionService;
@Controller
@RequestMapping("/questions/{questionId}/answers")
public class CommentController {
	@Autowired CommentService commentService; // 의존성 주입(Dependency Injection) 
	@Autowired QuestionService questionService;
		
	@PostMapping("")
	// public String createUser(Answer answer, Model model, HttpSession session) {
	public String createComment(@PathVariable Long questionId, String contents,HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Question question = questionService.getQuestionById(questionId);
		Comment newComment = new Comment(sessionUser, question, contents);
		commentService.saveComment(newComment);
		return String.format("redirect:/questions/%d", questionId);
	}
	

}
