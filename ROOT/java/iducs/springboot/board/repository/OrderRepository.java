package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findByNo(long no);
	OrderEntity findByOrdernoAndOrderpassword(String orderno, String password);
	
	List<OrderEntity> findAll();
	List<OrderEntity> findByUserNoAndStatusIn(long no, List<Integer> status);
	List<OrderEntity> findByUserNoAndDateBetween(long no, String date1, String date2);
	List<OrderEntity> findByNoOrderByNoDesc(long no);
	List<OrderEntity> findByDateOrderByNoAsc(String date);
	List<OrderEntity> findByDateBetween(String date1, String date2);
	List<OrderEntity> findByStatusAndDateBetween(int status, String date1, String date2);
	List<OrderEntity> findByUserNoAndDateBetweenAndStatusIn(long no, String date1, String date2, List<Integer> status);
	@Query(value="SELECT * FROM order_sheet as os LEFT JOIN order_productinfo ON os.no = order_productinfo.order_no WHERE os.date >= :date1 AND 2019-08-22 <= :date2 AND os.user_no = :userno AND os.status = 9 order by os.date asc", nativeQuery=true)
	List<OrderEntity> findByUserNoAndDateAndStatusLeftJoin(@Param("date1") String date1, @Param("date2") String date2, @Param("userno") long userno);
}
