package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.Question;
import iducs.springboot.board.entity.QuestionEntity;

public interface QuestionService {
	Question getQuestionById(long id); // primary key인 id 값을 가진 질문 조회
	List<Question> getQuestions(Long pageNo); // 모든 질문  조회
	List<Question> getQuestionsPage(Pageable pageable); // 모든 질문  조회
	Page<QuestionEntity> getQuestionsGetPage(Pageable pageable); // 페이징
	
	List<Question> getQuestionsByUser(String name); // name으로 조회
	List<Question> getQuestionsByPage(Pageable pageable); // 페이지로 조회
	
	void saveQuestion(Question question); // 질문 생성
	void updateQuestion(Question question); // 질문 수정
	void deleteQuestion(Question question); // 질문 삭제
}
