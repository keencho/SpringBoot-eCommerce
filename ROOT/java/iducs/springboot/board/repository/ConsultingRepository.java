package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ConsultingEntity;

@Repository
public interface ConsultingRepository extends JpaRepository<ConsultingEntity, Long>{
	ConsultingEntity findByNo(long no);
	
	List<ConsultingEntity> findAll();
	List<ConsultingEntity> findByUserNo(long no);
	List<ConsultingEntity> findByDateqBetween(String date1, String date2);
	List<ConsultingEntity> findByType(String type);
}
