package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Notice;
import iducs.springboot.board.entity.NoticeEntity;
import iducs.springboot.board.repository.NoticeRepository;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired NoticeRepository repository;
	@Override
	public Notice findByNo(long no) {
		NoticeEntity entity = repository.findByNo(no);
		if (entity == null)
			return null;
		return entity.buildDomain();
	}

	@Override
	public List<Notice> findAll() {
		List<Notice> notice = new ArrayList<Notice>();
		List<NoticeEntity> entities = repository.findAll();
		for(NoticeEntity entity : entities) {
			Notice notices = entity.buildDomain();
			notice.add(notices);
		}
		return notice;
	}

	@Override
	public List<Notice> findEverything(Pageable pageable) {
		List<Notice> notice = new ArrayList<Notice>();
		List<NoticeEntity> entities = repository.findEverything(pageable);
		for(NoticeEntity entity : entities) {
			Notice notices = entity.buildDomain();
			notice.add(notices);
		}
		return notice;
	}
	
	@Override
	public List<Notice> findBytitle(Pageable pageable, String title) {
		List<Notice> notice = new ArrayList<Notice>();
		List<NoticeEntity> entities = repository.findByTitleContaining(pageable, title);
		for(NoticeEntity entity : entities) {
			Notice notices = entity.buildDomain();
			notice.add(notices);
		}
		return notice;
	}

	@Override
	public Page<NoticeEntity> findAll(Pageable pageable) {
		Page<NoticeEntity> entities = repository.findAll(pageable);
		return entities;
	}

	@Override
	public Page<NoticeEntity> findByTitle(String title, Pageable pageable) {
		Page<NoticeEntity> entities = repository.findByTitleContaining(title, pageable);
		return entities;
	}

	@Override
	public void saveNotice(Notice notice) {
		NoticeEntity entity = new NoticeEntity();
		entity.buildEntity(notice);
		repository.save(entity);
	}

	@Override
	public void updateNotice(Notice notice) {
		NoticeEntity entity = new NoticeEntity();
		entity.buildEntity(notice);
		repository.save(entity);
	}

	@Override
	public void deleteNotice(Notice notice) {
		NoticeEntity entity = new NoticeEntity();
		entity.buildEntity(notice);
		repository.delete(entity);
	}

	@Override
	public List<Notice> findByContents(Pageable pageable, String contents) {
		List<Notice> notice = new ArrayList<Notice>();
		List<NoticeEntity> entities = repository.findByContentsContaining(pageable, contents);
		for(NoticeEntity entity : entities) {
			Notice notices = entity.buildDomain();
			notice.add(notices);
		}
		return notice;
	}

	@Override
	public List<Notice> findByTitleOrContents(Pageable pageable, String title, String contents) {
		List<Notice> notice = new ArrayList<Notice>();
		List<NoticeEntity> entities = repository.findByTitleContainingOrContentsContaining(pageable, title, contents);
		for(NoticeEntity entity : entities) {
			Notice notices = entity.buildDomain();
			notice.add(notices);
		}
		return notice;
	}

	@Override
	public Page<NoticeEntity> findByContents(String contents, Pageable pageable) {
		Page<NoticeEntity> entities = repository.findByContentsContaining(contents, pageable);
		return entities;
	}

	@Override
	public Page<NoticeEntity> findByTitleOrContents(String title, String contents, Pageable pageable) {
		Page<NoticeEntity> entities = repository.findByTitleContainingOrContentsContaining(title, contents, pageable);
		return entities;
	}


}
