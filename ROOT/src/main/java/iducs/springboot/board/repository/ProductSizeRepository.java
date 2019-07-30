package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.Entity;

import iducs.springboot.board.entity.ProductSizeEntity;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long>{
	ProductSizeEntity findByNo(long no);
	@Query(value="SELECT * FROM product_size WHERE product_no = :no GROUP BY product_no", nativeQuery=true)
	ProductSizeEntity findByNoNativeQuery(long no);
	
	List<ProductSizeEntity> findAll();
	
	@Query(value="select * from product_size join product on product.no = product_size.product_no where product.category_no = :no group by product_size.size_no", nativeQuery=true)
	List<ProductSizeEntity> findByCategoryNo(@Param("no") long no);

	List<ProductSizeEntity> findByProductNo(long product_no);

}
