package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductInfoEntity;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, Long>{
	ProductInfoEntity findByNo(long no);
	
	
	List<ProductInfoEntity> findAll(Sort sort);
}
