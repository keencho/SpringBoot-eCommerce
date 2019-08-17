package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Order;

public interface OrderService {
	Order findByNo(long no);
	
	List<Order> findAll();
	List<Order> findByNoOrderByNoDesc(long no);
	List<Order> findByDate(String date);
	
	void saveOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(Order order);
}
