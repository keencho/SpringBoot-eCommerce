package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductStockEntity;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStockEntity, Long>{
	ProductStockEntity findByNo(long no);
	
	List<ProductStockEntity> findByProductNo(long product_no);
	List<ProductStockEntity> findAll(Sort sort);

}
