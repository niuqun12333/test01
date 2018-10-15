package service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.OrderDao;
import entity.Area;
import entity.CarDetail;
import entity.City;
import entity.Location;
import entity.Order;
import entity.OrderDetail;
import entity.Province;
import service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Override
	public List<CarDetail> searchAdd(String ids) {
		List<CarDetail> list = orderDao.searchAdd(ids);
		return list;
	}

	@Override
	public List<Province> searchProvince() {
		List<Province> list = orderDao.searchProvince();
		return list;
	}

	@Override
	public List<Area> searchArea(City city) {
		List<Area> list = orderDao.searchArea(city);
		return list;
	}

	@Override
	public List<City> searchCity(Province prov) {
		List<City> list = orderDao.searchCity(prov);
		return list;
	}

	@Override
	public boolean addLocation(Location location) {
		int rs = orderDao.addLocation(location);
		return rs > 0;
	}

	@Override
	public List<Location> searchLocation(int u_id) {
		List<Location> list = orderDao.searchLocation(u_id);
		return list;
	}

	@Override
	public boolean addOrder(Order order,String cdIds) {
		int rs = orderDao.addOrder(order);
		int or_id = order.getId();
		List<CarDetail> list = orderDao.searchAdd(cdIds);
		for (int i = 0; i < list.size(); i++) {
			int number = list.get(i).getNumber();
			Double subtotal = list.get(i).getSubtotal();
			int p_id = list.get(i).getProduct().getId();
			OrderDetail od = new OrderDetail();
			od.setNum(number);
			od.setSubtotal(subtotal);
			rs = orderDao.addOrderDetail(od, or_id, p_id);
		}
		rs = orderDao.deleteCarDetail(cdIds);
		return rs > 0;
	}

	@Override
	public List<Order> searchOrderDetail(int u_id) {
		List<Order> OrderList = orderDao.searchOrderDetail(u_id);
		return OrderList;
	}

	@Override
	public boolean deleteOrder(Order order) {
		int rs = orderDao.deleteOrder(order);
		rs = orderDao.deleteOrderDetails(order);
		return rs>0;
	}

	@Override
	public boolean deleteLocation(Location location) {
		int rs = orderDao.deleteLocation(location);
		return rs>0;
	}

}
