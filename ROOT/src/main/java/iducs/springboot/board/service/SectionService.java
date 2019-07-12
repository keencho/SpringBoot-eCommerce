package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Division;
import iducs.springboot.board.domain.Section;

public interface SectionService {
	Section getSectionByNo(long no);
	
	List<Section> getSection();
	
	void saveSection(Section section);
	void updateSection(Section section);
	void deleteSection(Section section);
}
