package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Consulting;


public interface ConsultingService {
	Consulting findByNo(long no);
	List<Consulting> findAll();
	List<Consulting> findByUserNo(long no);
	List<Consulting> findByDateBetween(String date1, String date2);
	List<Consulting> findByType(String type);
	
	void saveConsulting(Consulting consulting);
	void updateConsulting(Consulting consulting);
	void deleteConsulting(Consulting consulting);
}
