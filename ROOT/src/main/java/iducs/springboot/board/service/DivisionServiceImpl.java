package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Division;
import iducs.springboot.board.entity.CategoryEntity;
import iducs.springboot.board.entity.DivisionEntity;
import iducs.springboot.board.repository.DivisionRepository;

@Service("divisionService")
public class DivisionServiceImpl implements DivisionService{
	
	@Autowired DivisionRepository repository;
	
	@Override
	public Division getDivisionByNo(long no) {
		DivisionEntity divisionEntity = repository.findByNo(no);
		if(divisionEntity == null)
			return null;
		return divisionEntity.buildDomain();
	}

	@Override
	public List<Division> getDivision() {
		List<Division> division = new ArrayList<Division>();
		List<DivisionEntity> entities = repository.findAll();
		for(DivisionEntity entity : entities) {
			Division div = entity.buildDomain();
			division.add(div);
		}
		return division;
	}

	@Override
	public void saveDivision(Division division) {
		DivisionEntity entity = new DivisionEntity();
		entity.buildEntity(division);
		repository.save(entity);
	}

	@Override
	public void updateDivision(Division division) {
		DivisionEntity entity = new DivisionEntity();
		entity.buildEntity(division);
		repository.save(entity);
	}

	@Override
	public void deleteDivision(Division division) {
		DivisionEntity entity = new DivisionEntity();
		entity.buildEntity(division);
		repository.delete(entity);
	}





}
