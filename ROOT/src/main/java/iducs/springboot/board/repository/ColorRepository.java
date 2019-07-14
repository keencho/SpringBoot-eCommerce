package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ColorEntity;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long>{
	ColorEntity findByNo(long no);
	
	List<ColorEntity> findAll(Sort sort);
}
