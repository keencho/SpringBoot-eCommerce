package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Order;

public interface OrderService {
	Order findByNo(long no);
	
	List<Order> findAll();
	List<Order> findByUserNoOrder(long no, List<Integer> status);
	List<Order> findByUserNoAndDateBetween(long no, String date1, String date2);
	List<Order> findByNoOrderByNoDesc(long no);
	List<Order> findByDate(String date);
	List<Order> findByDateBetween(String date1, String date2);
	List<Order> findByStatusAndDateBetween(int status, String date1, String date2);
	List<Order> findByUserNoAndStatusAndDateBetween(long no, String date1, String date2, List<Integer> status);
	List<Order> findByUserNoandStatusAndDateLeftJoin(String date1, String date2, long userno);
	
	void saveOrder(Order order);
	void saveOrderNonUser(Order order);
	void updateOrder(Order order);
	void deleteOrder(Order order);
}
