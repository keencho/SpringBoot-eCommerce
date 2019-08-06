package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductQuestionEntity;

@Repository
public interface ProductQuestionRepository extends JpaRepository<ProductQuestionEntity, Long>{
	ProductQuestionEntity findByNo(long no);
	List<ProductQuestionEntity> findByProductNo(long no);
	List<ProductQuestionEntity> findAll();
}
