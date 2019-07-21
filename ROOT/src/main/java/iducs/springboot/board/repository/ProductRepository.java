package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	ProductEntity findByNo(long no);
	List<ProductEntity> findByCategoryNo(Pageable pageable, long no);
	Page<ProductEntity> findByCategoryNo(long no, Pageable pageable);
	
	List<ProductEntity> findAll(Sort sort);
}
