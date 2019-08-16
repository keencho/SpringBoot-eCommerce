package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductQuestionEntity;

@Repository
public interface ProductQuestionRepository extends JpaRepository<ProductQuestionEntity, Long>{
	ProductQuestionEntity findByNo(long no);
	List<ProductQuestionEntity> findByProductNo(long no, Pageable questionPageable);
	List<ProductQuestionEntity> findByProductNoAndStatus(long no, Pageable questionPageable, int status);
	List<ProductQuestionEntity> findByProductNo(long no);
	List<ProductQuestionEntity> findAll();
	
	Page<ProductQuestionEntity> findByProductNo(Pageable questionPageable, long no);
	Page<ProductQuestionEntity> findByProductNoAndStatus(Pageable questionPageable, long no, int status);
}
