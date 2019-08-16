package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Section;

public interface SectionService {
	Section getSectionByNo(long no);
	
	List<Section> getSection();
	List<Section> getSectionByDivisionNo(long no);
	
	void saveSection(Section section);
	void updateSection(Section section);
	void deleteSection(Section section);
}
