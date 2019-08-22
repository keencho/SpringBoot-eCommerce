package iducs.springboot.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iducs.springboot.board.domain.OrderInfo;
import iducs.springboot.board.entity.OrderInfoEntity;
import iducs.springboot.board.repository.OrderInfoRepository;

@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService{
	
	@Autowired OrderInfoRepository repository;

	@Override
	public OrderInfo findByNo(long no) {
		OrderInfoEntity orderInfoEntity = repository.findByNo(no);
		if(orderInfoEntity == null)
			return null;
		return orderInfoEntity.buildDomain();
	}

	@Override
	public List<OrderInfo> findByOrderNo(long no) {
		List<OrderInfo> order = new ArrayList<OrderInfo>();
		List<OrderInfoEntity> entities = repository.findByOrderNoOrderByNoAsc(no);
		for(OrderInfoEntity entity : entities) {
			OrderInfo ord = entity.buildDomain();
			order.add(ord);
		}
		return order;
	}

	@Override
	public void saveOrderInfo(OrderInfo orderInfo) {
		OrderInfoEntity entity = new OrderInfoEntity();
		entity.buildEntity(orderInfo);
		repository.save(entity);
	}

	@Override
	public void updateOrderInfo(OrderInfo orderInfo) {
		OrderInfoEntity entity = new OrderInfoEntity();
		entity.buildEntity(orderInfo);
		repository.save(entity);
	}

	@Override
	public void deleteOrderInfo(OrderInfo orderInfo) {
		OrderInfoEntity entity = new OrderInfoEntity();
		entity.buildEntity(orderInfo);
		repository.delete(entity);
	}

	@Override
	public List<OrderInfo> findByUserNoandStatusAndDateLeftJoin(String date1, String date2, long userno) {
		List<OrderInfo> order = new ArrayList<OrderInfo>();
		List<OrderInfoEntity> entities = repository.findByUserNoAndDateAndStatusLeftJoin(date1, date2, userno);
		for(OrderInfoEntity entity : entities) {
			OrderInfo ord = entity.buildDomain();
			order.add(ord);
		}
		return order;
	}
}
