package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Comment;
import iducs.springboot.board.domain.Question;

public interface CommentService {
	Comment getCommentById(long id); // primary key인 id 값을 가진 질문 조회
	List<Comment> getComment(); // 모든 질문  조회
	
	void saveComment(Comment question); // 질문 생성
	void updateComment(Comment question); // 질문 수정
	void deleteComment(Comment question); // 질문 삭제
}
