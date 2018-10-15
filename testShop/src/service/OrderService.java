package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Area;
import entity.CarDetail;
import entity.City;
import entity.Location;
import entity.Order;
import entity.Province;

public interface OrderService {

	List<CarDetail> searchAdd(String ids);

	List<Province> searchProvince();

	List<Area> searchArea(City city);

	List<City> searchCity(Province prov);

	boolean addLocation(Location location);

	List<Location> searchLocation(int u_id);

	boolean addOrder(Order order, String cdIds);
	
	List<Order> searchOrderDetail(int u_id);

	boolean deleteOrder(Order order);

	boolean deleteLocation(Location location);

	

	

	
	
}
