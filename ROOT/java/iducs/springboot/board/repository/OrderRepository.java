package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findByNo(long no);
	
	List<OrderEntity> findAll();
	List<OrderEntity> findByUserNoAndStatusIn(long no, List<Integer> status);
	List<OrderEntity> findByUserNoAndDateBetween(long no, String date1, String date2);
	List<OrderEntity> findByNoOrderByNoDesc(long no);
	List<OrderEntity> findByDateOrderByNoAsc(String date);
	List<OrderEntity> findByDateBetween(String date1, String date2);
	List<OrderEntity> findByStatusAndDateBetween(int status, String date1, String date2);
	List<OrderEntity> findByUserNoAndDateBetweenAndStatusIn(long no, String date1, String date2, List<Integer> status);
}
