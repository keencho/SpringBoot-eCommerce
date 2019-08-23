package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductReviewEntity;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Long>{
	ProductReviewEntity findByNo(long no);
	ProductReviewEntity findByProductNo(long no);
	
	List<ProductReviewEntity> findAll();
	List<ProductReviewEntity> findByUserNo(long no);
	List<ProductReviewEntity> findByProductNoOrderByNoDesc(long no);
	List<ProductReviewEntity> findByProductNoOrderByNoDesc(long no, Pageable pageable);
	List<ProductReviewEntity> findByInfoNo(long no);
	
	Page<ProductReviewEntity> findByProductNo(Pageable pageable, long no);
}
