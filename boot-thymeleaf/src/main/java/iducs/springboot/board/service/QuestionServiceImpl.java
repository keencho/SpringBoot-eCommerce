package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Comment;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.domain.User;
import iducs.springboot.board.entity.CommentEntity;
import iducs.springboot.board.entity.QuestionEntity;
import iducs.springboot.board.entity.UserEntity;
import iducs.springboot.board.repository.QuestionRepository;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired QuestionRepository repository;

	@Override
	public Question getQuestionById(long id) {
		QuestionEntity entity = repository.findById(id).get();
		
		Question question = entity.buildDomain();
		
		List<Comment> commentList = new ArrayList<Comment>();
		for(CommentEntity commentEntity : entity.getAnswers())
			commentList.add(commentEntity.buildDomain());
		question.setAnswers(commentList);
		
		return question;
	}

	@Override
	public List<Question> getQuestions(Long pageNo) {
		/*
		 * 1. Repository로 부터 모든 자료를 가져와 Enitiy 리스트에 저장한다.
		 * 2. 
		 */
		PageRequest pageRequest = PageRequest.of((int) (pageNo - 1), 3, new Sort(Sort.Direction.ASC, "id"));
		Page<QuestionEntity> entities = repository.findAll(pageRequest);
		
		List<Question> questions = new ArrayList<Question>();
		for(QuestionEntity entity : entities) {
			Question question = entity.buildDomain();
			questions.add(question);
		}
		return questions;			
	}
	
	public List<Question> getQuestionsPage(Pageable pageable) {
		List<Question> questions = new ArrayList<Question>();
		Page<QuestionEntity> entities = repository.findAll(pageable);
		for(QuestionEntity entity : entities) {
			Question question = entity.buildDomain();
			questions.add(question);
		}
		return questions;
	}
	
	public Page<QuestionEntity> getQuestionsGetPage(Pageable pageable) {
		Page<QuestionEntity> entities = repository.findAll(pageable);

		return entities;
	}
	

	@Override
	public List<Question> getQuestionsByUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionsByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestion(Question question) {
		QuestionEntity entity = new QuestionEntity();
		entity.buildEntity(question);
		repository.save(entity);
		
	}

	@Override
	public void updateQuestion(Question question) {
		QuestionEntity entity = new QuestionEntity();
		entity.buildEntity(question);
		repository.save(entity);
		
	}

	@Override
	public void deleteQuestion(Question question) {
		QuestionEntity entity = new QuestionEntity();
		entity.buildEntity(question);
		repository.delete(entity);
		
	}

	

}
