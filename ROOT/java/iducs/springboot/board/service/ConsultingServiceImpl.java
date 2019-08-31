package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Consulting;
import iducs.springboot.board.entity.ConsultingEntity;
import iducs.springboot.board.repository.ConsultingRepository;

@Service("consultingService")
public class ConsultingServiceImpl implements ConsultingService{
	
	@Autowired ConsultingRepository repository;

	@Override
	public Consulting findByNo(long no) {
		ConsultingEntity cE = repository.findByNo(no);
		if(cE == null)
			return null;
		return cE.buildDomain();
	}

	@Override
	public List<Consulting> findAll() {
		List<Consulting> cs = new ArrayList<Consulting>();
		List<ConsultingEntity> cses = repository.findAll();
		for(ConsultingEntity et : cses) {
			Consulting cst = et.buildDomain();
			cs.add(cst);
		}
		return cs;
	}

	@Override
	public List<Consulting> findByUserNo(long no) {
		List<Consulting> cs = new ArrayList<Consulting>();
		List<ConsultingEntity> cses = repository.findByUserNo(no);
		for(ConsultingEntity et : cses) {
			Consulting cst = et.buildDomain();
			cs.add(cst);
		}
		return cs;
	}

	@Override
	public List<Consulting> findByTypeAndDateBetween(String type, String date1, String date2) {
		List<Consulting> cs = new ArrayList<Consulting>();
		List<ConsultingEntity> cses = repository.findByTypeAndDateqBetween(type, date1, date2);
		for(ConsultingEntity et : cses) {
			Consulting cst = et.buildDomain();
			cs.add(cst);
		}
		return cs;
	}

	@Override
	public List<Consulting> findByType(String type) {
		List<Consulting> cs = new ArrayList<Consulting>();
		List<ConsultingEntity> cses = repository.findByType(type);
		for(ConsultingEntity et : cses) {
			Consulting cst = et.buildDomain();
			cs.add(cst);
		}
		return cs;
	}

	@Override
	public void saveConsulting(Consulting consulting) {
		ConsultingEntity entity = new ConsultingEntity();
		entity.buildEntity(consulting);
		repository.save(entity);
	}

	@Override
	public void updateConsulting(Consulting consulting) {
		ConsultingEntity entity = new ConsultingEntity();
		entity.buildEntity(consulting);
		repository.save(entity);
	}

	@Override
	public void deleteConsulting(Consulting consulting) {
		ConsultingEntity entity = new ConsultingEntity();
		entity.buildEntity(consulting);
		repository.delete(entity);
	}

	@Override
	public List<Consulting> findByDateBetween(String date1, String date2) {
		List<Consulting> cs = new ArrayList<Consulting>();
		List<ConsultingEntity> cses = repository.findByDateqBetween(date1, date2);
		for(ConsultingEntity et : cses) {
			Consulting cst = et.buildDomain();
			cs.add(cst);
		}
		return cs;
	}
	
}
