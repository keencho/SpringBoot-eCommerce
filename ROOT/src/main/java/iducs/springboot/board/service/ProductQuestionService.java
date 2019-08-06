package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.ProductQuestion;

public interface ProductQuestionService {
	ProductQuestion getProductQuestionByNo(long no);
	List<ProductQuestion> getProductQuestion(long no);
	List<ProductQuestion> findAll();
	
	void saveProductQuestion(ProductQuestion productquestion);
	void updateProductQuestion(ProductQuestion productquestion);
	void deleteProductQuestion(ProductQuestion productquestion);
}
