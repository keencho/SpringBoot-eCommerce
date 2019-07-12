package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import iducs.springboot.board.domain.Section;
import iducs.springboot.board.entity.SectionEntity;
import iducs.springboot.board.repository.SectionRepository;

@Service("sectionService")
public class SectionServiceImpl implements SectionService{
	
	@Autowired SectionRepository repository;

	@Override
	public Section getSectionByNo(long no) {
		SectionEntity sectionEntity = repository.findByNo(no);
		if(sectionEntity == null)
			return null;
		return sectionEntity.buildDomain();
	}

	@Override
	public List<Section> getSection() {
		List<Section> section = new ArrayList<Section>();
		List<SectionEntity> entities = repository.findAll();
		for(SectionEntity entity : entities) {
			Section sec = entity.buildDomain();
			section.add(sec);
		}
		return section;
	}

	@Override
	public void saveSection(Section section) {
		SectionEntity entity = new SectionEntity();
		entity.buildEntity(section);
		repository.save(entity);
	}

	@Override
	public void updateSection(Section section) {
		SectionEntity entity = new SectionEntity();
		entity.buildEntity(section);
		repository.save(entity);
	}

	@Override
	public void deleteSection(Section section) {
		SectionEntity entity = new SectionEntity();
		entity.buildEntity(section);
		repository.delete(entity);
	}
	
	
}
