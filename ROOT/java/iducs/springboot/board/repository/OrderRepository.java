package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findByNo(long no);
	
	List<OrderEntity> findAll();
	List<OrderEntity> findByNoOrderByNoDesc(long no);
	List<OrderEntity> findByDateOrderByNoAsc(String date);
}
