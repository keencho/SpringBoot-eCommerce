package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.DivisionEntity;
import iducs.springboot.board.entity.ProductEntity;

@Repository
public interface DivisionRepository extends JpaRepository<DivisionEntity, Long>{
	DivisionEntity findByNo(long no);
	List<DivisionEntity> findAll(Sort sort);
}
