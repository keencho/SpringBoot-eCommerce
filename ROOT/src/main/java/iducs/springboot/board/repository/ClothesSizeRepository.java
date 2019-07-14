package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.ClothesSizeEntity;

@Repository
public interface ClothesSizeRepository extends JpaRepository<ClothesSizeEntity, Long>{
	ClothesSizeEntity findByNo(long no);
	
	List<ClothesSizeEntity> findAll(Sort sort);
}
