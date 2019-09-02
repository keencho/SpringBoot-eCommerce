package iducs.springboot.board.service;

import java.util.List;

import iducs.springboot.board.domain.OrderInfo;

public interface OrderInfoService {
	OrderInfo findByNo(long no);
	List<OrderInfo> findByOrderNo(long no);
	List<OrderInfo> findByUserNoandStatusAndDateLeftJoin(String date1, String date2, long userno);
	List<OrderInfo> findOrderRand5(int count);
	
	void saveOrderInfo(OrderInfo orderInfo);
	void updateOrderInfo(OrderInfo orderInfo);
	void deleteOrderInfo(OrderInfo orderInfo);
}
