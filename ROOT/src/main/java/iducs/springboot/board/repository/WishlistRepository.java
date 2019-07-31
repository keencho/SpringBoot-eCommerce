package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import iducs.springboot.board.entity.SectionEntity;
import iducs.springboot.board.entity.WishlistEntity;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Long>{
	List<WishlistEntity> findByUserNo(long no);
	
	WishlistEntity findByNo(long no);
	
	@Query(value="SELECT * FROM wishlist WHERE product_no = :productno and user_no = :userno GROUP BY product_no", nativeQuery=true)
	WishlistEntity findDuplicate(@Param("productno") long productno, @Param("userno") long userno);
	
	List<WishlistEntity> deleteAllByUserNo(long no);

}
