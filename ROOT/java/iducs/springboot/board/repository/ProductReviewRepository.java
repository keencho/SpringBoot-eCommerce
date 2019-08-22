package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductReviewEntity;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Long>{
	ProductReviewEntity findByNo(long no);
	
	List<ProductReviewEntity> findAll();
	List<ProductReviewEntity> findByUserNo(long no);
	List<ProductReviewEntity> findByProductNo(long no);
	List<ProductReviewEntity> findByInfoNo(long no);
}
