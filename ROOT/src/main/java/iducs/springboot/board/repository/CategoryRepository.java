package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findByNo(long no);
	CategoryEntity findByName(String name);
	
	List<CategoryEntity> findByNameOrderByNoAsc(String name);

}
