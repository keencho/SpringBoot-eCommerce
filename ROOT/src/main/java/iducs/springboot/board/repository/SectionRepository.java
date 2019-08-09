package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import iducs.springboot.board.entity.SectionEntity;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, Long>{
	SectionEntity findByNo(long no);
	
	List<SectionEntity> findAll(Sort sort);
	List<SectionEntity> findByDivisionNo(long no);

}
