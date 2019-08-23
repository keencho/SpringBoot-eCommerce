package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.ProductReview;
import iducs.springboot.board.entity.ProductReviewEntity;

public interface ProductReviewService {
	ProductReview findByNo(long no);
	ProductReview getByProductNo(long no);
	
	List<ProductReview> findAll();
	List<ProductReview> findByUserNo(long no);
	List<ProductReview> findByProductNo(long no);
	List<ProductReview> findByProductNo(long no, Pageable pageable);
	List<ProductReview> findByInfoNo(long no);
	Page<ProductReviewEntity> findByProductNoPage(long no, Pageable pageable);
	
	void saveProductReview(ProductReview productreview);
	void updateProductReview(ProductReview productreview);
	void deleteProductReview(ProductReview productreview);
}
