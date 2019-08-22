package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
	OrderInfoEntity findByNo(long no);

	List<OrderInfoEntity> findByOrderNoOrderByNoAsc(long no);
	@Query(value="SELECT * FROM order_productinfo LEFT JOIN order_sheet as os ON os.no = order_productinfo.order_no WHERE os.date >= :date1 AND os.date <= :date2 AND os.user_no = :userno AND os.status = 9 order by os.date asc", nativeQuery=true)
	List<OrderInfoEntity> findByUserNoAndDateAndStatusLeftJoin(@Param("date1") String date1, @Param("date2") String date2, @Param("userno") long userno);
}
