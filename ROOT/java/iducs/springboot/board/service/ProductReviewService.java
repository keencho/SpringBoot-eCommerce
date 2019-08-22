package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.ProductReview;

public interface ProductReviewService {
	ProductReview findByNo(long no);
	
	List<ProductReview> findAll();
	List<ProductReview> findByUserNo(long no);
	List<ProductReview> findByProductNo(long no);
	List<ProductReview> findByInfoNo(long no);
	
	void saveProductReview(ProductReview productreview);
	void updateProductReview(ProductReview productreview);
	void deleteProductReview(ProductReview productreview);
}
