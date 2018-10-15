package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrderDao;
import entity.Order;
import service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;

	@Override
	public List<Order> searchOrder(Order order) {
		List<Order> list = orderDao.searchOrder(order);
		return list;
	}

	@Override
	public boolean updateCondition(Order order) {
		int rs = orderDao.updateCondition(order);
		return rs>0;
	}

	@Override
	public boolean deleteOrder(Order order) {
		int rs = orderDao.deleteOrder(order);
		rs = orderDao.deleteOrderDetails(order);
		return rs>0;
	}

}
