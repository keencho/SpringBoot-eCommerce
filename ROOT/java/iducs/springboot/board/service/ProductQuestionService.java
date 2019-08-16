package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.entity.ProductQuestionEntity;

public interface ProductQuestionService {
	ProductQuestion getProductQuestionByNo(long no);
	List<ProductQuestion> getProductQuestion(long no, Pageable questionPageable);
	List<ProductQuestion> getProductQuestionStatus(long no, int status, Pageable questionPageable);
	List<ProductQuestion> getProductQuestionOriginal(long no);
	List<ProductQuestion> findAll();
	Page<ProductQuestionEntity> getProductQuestionPage(Pageable questionPageable, long no);
	Page<ProductQuestionEntity> getProductQuestionStatusPage(Pageable questionPageable, long no, int status);
	
	void saveProductQuestion(ProductQuestion productquestion);
	void updateProductQuestion(ProductQuestion productquestion);
	void deleteProductQuestion(ProductQuestion productquestion);
}
