package iducs.springboot.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import iducs.springboot.board.entity.NoticeEntity;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>{
	NoticeEntity findByNo(long no);
	
	List<NoticeEntity> findAll();
	@Query(value="SELECT * FROM notice",  nativeQuery=true)
	List<NoticeEntity> findEverything(Pageable pageable);
	Page<NoticeEntity> findAll(Pageable pageable);
	
	List<NoticeEntity> findByTitleContaining(Pageable pageable, String title);
	Page<NoticeEntity> findByTitleContaining(String title, Pageable pageable);
	
	List<NoticeEntity> findByContentsContaining(Pageable pageable, String contents);
	Page<NoticeEntity> findByContentsContaining(String contents, Pageable pageable);
	
	List<NoticeEntity> findByTitleContainingOrContentsContaining(Pageable pageable, String title, String contents);
	Page<NoticeEntity> findByTitleContainingOrContentsContaining(String title, String contents, Pageable pageable);
}
