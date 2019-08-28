package iducs.springboot.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.entity.NoticeEntity;


public interface NoticeService {
	Notice findByNo(long no);
	List<Notice> findAll();
	List<Notice> findEverything(Pageable pageable);
	List<Notice> findBytitle(Pageable pageable,String title);
	
	Page<NoticeEntity> findAll(Pageable pageable);
	Page<NoticeEntity> findByTitle(String title, Pageable pageable);
	
	void saveNotice(Notice notice);
	void updateNotice(Notice notice);
	void deleteNotice(Notice notice);
}
