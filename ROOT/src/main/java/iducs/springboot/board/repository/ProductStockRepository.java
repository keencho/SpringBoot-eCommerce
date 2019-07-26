package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductStockEntity;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStockEntity, Long>{
	ProductStockEntity findByNo(long no);
	
	List<ProductStockEntity> findByProductNo(long product_no);
	List<ProductStockEntity> findAll(Sort sort);
	
	@Query(value="select * from product_stock join product on product.no = product_stock.product_no where product.category_no = :no group by product_stock.size_no", nativeQuery=true)
	List<ProductStockEntity> findByCategoryNo(@Param("no") long no);
	
	@Query(value="select * from product_stock join product on product.no = product_stock.product_no where product.category_no = :no group by product_stock.color_no", nativeQuery=true)
	List<ProductStockEntity> findByCategoryNoColor(@Param("no") long no);
	
	@Query(value="select * from product_stock join product on product.no = product_stock.product_no where product.division_no = :no group by product_stock.size_no", nativeQuery=true)
	List<ProductStockEntity> findByDivisionNo(@Param("no") long no);
	
	@Query(value="select * from product_stock join product on product.no = product_stock.product_no where product.division_no = :no group by product_stock.color_no", nativeQuery=true)
	List<ProductStockEntity> findByDivisionNoColor(@Param("no") long no);
	
	@Query(value="select * from product_stock join product on product.no = product_stock.product_no where product.section_no = :no group by product_stock.size_no", nativeQuery=true)
	List<ProductStockEntity> findBySectionNo(@Param("no") long no);
	
	@Query(value="select * from product_stock join product on product.no = product_stock.product_no where product.section_no = :no group by product_stock.color_no", nativeQuery=true)
	List<ProductStockEntity> findBySectionNoColor(@Param("no") long no);

}
