package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Comment;
import iducs.springboot.board.domain.Question;
import iducs.springboot.board.entity.CommentEntity;
import iducs.springboot.board.entity.QuestionEntity;
import iducs.springboot.board.repository.CommentRepository;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired 
	private CommentRepository repository;
	
	@Override
	public Comment getCommentById(long id) {
		CommentEntity entity = repository.findById(id).get();
		
		Comment answer = entity.buildDomain();
		
		return answer;
	}

	@Override
	public List<Comment> getComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveComment(Comment comment) {
		CommentEntity entity = new CommentEntity();
		entity.buildEntity(comment);
		repository.save(entity);
	}

	@Override
	public void updateComment(Comment comment) {
		CommentEntity entity = new CommentEntity();
		entity.buildEntity(comment);
		repository.save(entity);

	}

	@Override
	public void deleteComment(Comment comment) {
		CommentEntity entity = new CommentEntity();
		entity.buildEntity(comment);
		repository.delete(entity);

	}

}
