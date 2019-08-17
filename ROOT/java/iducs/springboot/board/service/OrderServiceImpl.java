package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.Order;
import iducs.springboot.board.domain.ProductQuestion;
import iducs.springboot.board.entity.OrderEntity;
import iducs.springboot.board.entity.ProductQuestionEntity;
import iducs.springboot.board.repository.OrderRepository;
import iducs.springboot.board.repository.ProductQuestionRepository;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired OrderRepository repository;

	@Override
	public Order findByNo(long no) {
		OrderEntity orderEntity = repository.findByNo(no);
		if(orderEntity == null)
			return null;
		return orderEntity.buildDomain();
	}

	@Override
	public List<Order> findAll() {
		List<Order> order = new ArrayList<Order>();
		List<OrderEntity> entities = repository.findAll();
		for(OrderEntity entity : entities) {
			Order ord = entity.buildDomain();
			order.add(ord);
		}
		return order;
	}

	@Override
	public List<Order> findByNoOrderByNoDesc(long no) {
		List<Order> order = new ArrayList<Order>();
		List<OrderEntity> entities = repository.findByNoOrderByNoDesc(no);
		for(OrderEntity entity : entities) {
			Order ord = entity.buildDomain();
			order.add(ord);
		}
		return order;
	}

	@Override
	public void saveOrder(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.buildEntity(order);
		repository.save(entity);
	}

	@Override
	public void updateOrder(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.buildEntity(order);
		repository.save(entity);
	}

	@Override
	public void deleteOrder(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.buildEntity(order);
		repository.delete(entity);
	}

	@Override
	public List<Order> findByDate(String date) {
		List<Order> order = new ArrayList<Order>();
		List<OrderEntity> entities = repository.findByDateOrderByNoAsc(date);
		for(OrderEntity entity : entities) {
			Order ord = entity.buildDomain();
			order.add(ord);
		}
		return order;
	}

}
