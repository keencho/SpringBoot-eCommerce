package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findByNo(long no);
	
	@Query(value="SELECT * FROM order_sheet GROUP BY order_no", nativeQuery=true)
	List<OrderEntity> findAll();
	
	List<OrderEntity> findByNoOrderByNoDesc(long no);
}
