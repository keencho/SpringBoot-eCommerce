package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductSizeEntity;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long>{
	ProductSizeEntity findByNo(long no);
	
	List<ProductSizeEntity> findByProductNo(long product_no);
	List<ProductSizeEntity> findAll(Sort sort);

}
