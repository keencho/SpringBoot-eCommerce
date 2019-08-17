package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.OrderInfoEntity;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfoEntity, Long>{
	OrderInfoEntity findByNo(long no);

	List<OrderInfoEntity> findByOrderNoOrderByNoAsc(long no);
}
