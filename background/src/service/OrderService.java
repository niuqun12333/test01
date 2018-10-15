package service;

import java.util.List;

import entity.Order;

public interface OrderService {

	List<Order> searchOrder(Order order);

	boolean updateCondition(Order order);

	boolean deleteOrder(Order order);

}
