package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductEntity;
import iducs.springboot.board.entity.ProductStockEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	ProductEntity findByNo(long no);
	List<ProductEntity> findByCategoryNo(Pageable pageable, long no);
	List<ProductEntity> findByDivisionNo(Pageable pageable, long no);
	List<ProductEntity> findBySectionNo(Pageable pageable, long no);
	Page<ProductEntity> findByCategoryNo(long no, Pageable pageable);
	Page<ProductEntity> findByDivisionNo(long no, Pageable pageable);
	Page<ProductEntity> findBySectionNo(long no, Pageable pageable);
	
	@Query(value="SELECT * FROM product as p LEFT JOIN product_stock ON p.no = product_stock.product_no WHERE product_stock.size_no IN (:size) and product_stock.color_no IN (:color) AND p.category_no = :no AND p.listprice >= :price1 AND p.listprice <= :price2 GROUP BY p.name", nativeQuery=true)
	List<ProductEntity> findByCategoryNo(@Param("no") long categoryno, @Param("size") String[] size, @Param("color") String[] color, @Param("price1") long price1, @Param("price2") long price2, Pageable pageable);
	@Query(value="SELECT * FROM product as p LEFT JOIN product_stock ON p.no = product_stock.product_no WHERE product_stock.size_no IN (:size) and product_stock.color_no IN (:color) AND p.category_no = :no AND p.listprice >= :price1 AND p.listprice <= :price2 GROUP BY p.name", nativeQuery=true)
	Page<ProductEntity> findByCategoryNoPage(@Param("no") long categoryno, Pageable pageable, @Param("size") String[] size, @Param("color") String[] color, @Param("price1") long price1, @Param("price2") long price2);
	
	@Query(value="SELECT * FROM product as p LEFT JOIN product_stock ON p.no = product_stock.product_no WHERE product_stock.size_no IN (:size) and product_stock.color_no IN (:color) AND p.division_no = :no AND p.listprice >= :price1 AND p.listprice <= :price2 GROUP BY p.name", nativeQuery=true)
	List<ProductEntity> findByDivisionNo(@Param("no") long divisionno, @Param("size") String[] size, @Param("color") String[] color, @Param("price1") long price1, @Param("price2") long price2, Pageable pageable);
	@Query(value="SELECT * FROM product as p LEFT JOIN product_stock ON p.no = product_stock.product_no WHERE product_stock.size_no IN (:size) and product_stock.color_no IN (:color) AND p.division_no = :no AND p.listprice >= :price1 AND p.listprice <= :price2 GROUP BY p.name", nativeQuery=true)
	Page<ProductEntity> findByDivisionNoPage(@Param("no") long divisionno, Pageable pageable, @Param("size") String[] size, @Param("color") String[] color, @Param("price1") long price1, @Param("price2") long price2);
	
	@Query(value="SELECT * FROM product as p LEFT JOIN product_stock ON p.no = product_stock.product_no WHERE product_stock.size_no IN (:size) and product_stock.color_no IN (:color) AND p.section_no = :no AND p.listprice >= :price1 AND p.listprice <= :price2 GROUP BY p.name", nativeQuery=true)
	List<ProductEntity> findBySectionNo(@Param("no") long sectionno, @Param("size") String[] size, @Param("color") String[] color, @Param("price1") long price1, @Param("price2") long price2, Pageable pageable);
	@Query(value="SELECT * FROM product as p LEFT JOIN product_stock ON p.no = product_stock.product_no WHERE product_stock.size_no IN (:size) and product_stock.color_no IN (:color) AND p.section_no = :no AND p.listprice >= :price1 AND p.listprice <= :price2 GROUP BY p.name", nativeQuery=true)
	Page<ProductEntity> findBySectionNoPage(@Param("no") long sectionno, Pageable pageable, @Param("size") String[] size, @Param("color") String[] color, @Param("price1") long price1, @Param("price2") long price2);
	
	List<ProductEntity> findAll(Sort sort);
}
