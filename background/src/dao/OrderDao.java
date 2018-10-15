package dao;

import java.util.List;

import entity.Order;

public interface OrderDao {

	List<Order> searchOrder(Order order);

	int updateCondition(Order order);

	int deleteOrder(Order order);

	int deleteOrderDetails(Order order);

}
