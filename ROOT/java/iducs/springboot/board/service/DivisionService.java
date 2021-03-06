package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Division;

public interface DivisionService {
	Division getDivisionByNo(long no);

	List<Division> getDivision();
	List<Division> getDivisionByCategoryNo(long no);
	
	void saveDivision(Division division);
	void updateDivision(Division division);
	void deleteDivision(Division division);
}
