package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.Order;
import iducs.springboot.board.domain.OrderInfo;

public interface OrderInfoService {
	OrderInfo findByNo(long no);
	List<OrderInfo> findByOrderNo(long no);
	
	void saveOrderInfo(OrderInfo orderInfo);
	void updateOrderInfo(OrderInfo orderInfo);
	void deleteOrderInfo(OrderInfo orderInfo);
}
