package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;

import iducs.springboot.board.entity.ProductSizeEntity;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long>{
	ProductSizeEntity findByNo(long no);
	
	List<ProductSizeEntity> findAll();
	List<ProductSizeEntity> findDistinctProductSizeBySizeNo();

	List<ProductSizeEntity> findByProductNo(long product_no);

}
