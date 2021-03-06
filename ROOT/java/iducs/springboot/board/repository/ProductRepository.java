package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	ProductEntity findByNo(long no);
	List<ProductEntity> findByCategoryNo(Pageable pageable, long no);
	List<ProductEntity> findByDivisionNo(Pageable pageable, long no);
	List<ProductEntity> findBySectionNo(Pageable pageable, long no);
	List<ProductEntity> findBySectionNo(long no);
	@Query(value="SELECT * FROM product WHERE category_no = :no ORDER BY rand() LIMIT 5", nativeQuery=true)
	List<ProductEntity> findTop5ByCategoryNo(long no);
	@Query(value="SELECT * FROM product WHERE category_no = :no ORDER BY rand() LIMIT 6", nativeQuery=true)
	List<ProductEntity> findTop6ByCategoryNo(long no);
	@Query(value="SELECT * FROM product WHERE division_no = :no ORDER BY rand() LIMIT 3", nativeQuery=true)
	List<ProductEntity> findTop3ByDivisionNo(long no);
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
	
	@Query(value="SELECT * FROM product ORDER BY no DESC LIMIT 8", nativeQuery = true)
	List<ProductEntity> findTop8OrderByNoDesc();
	
	@Query(value="SELECT * FROM product ORDER BY rand() LIMIT 5", nativeQuery = true)
	List<ProductEntity> findRand5();
	
	@Query(value="SELECT * FROM product ORDER BY rand() LIMIT 3", nativeQuery = true)
	List<ProductEntity> findRand3();
	
	@Query(value="SELECT * FROM product WHERE regdate > :date ORDER BY rand() LIMIT 5", nativeQuery = true)
	List<ProductEntity> findNewRand5(@Param("date") String date);
	
	@Query(value="SELECT * FROM product WHERE discount > :discount ORDER BY rand() LIMIT 5", nativeQuery = true)
	List<ProductEntity> findSaleRand5(@Param("discount") int discount);
	
	List<ProductEntity> findByNameContaining(Pageable pageable, String name);
	Page<ProductEntity> findByNameContaining(String name, Pageable pageable);
	List<ProductEntity> findByNameContainingOrderByNameAsc(String search);
	
	List<ProductEntity> findByCategoryNoAndNameContaining(Pageable pageable, long no, String name);
	Page<ProductEntity> findByCategoryNoAndNameContaining(long no, String name, Pageable pageable);
	
	List<ProductEntity> findByDivisionNoAndNameContaining(Pageable pageable, long no, String name);
	Page<ProductEntity> findByDivisionNoAndNameContaining(long no, String name, Pageable pageable);
}
