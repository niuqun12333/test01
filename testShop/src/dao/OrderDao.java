package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Area;
import entity.CarDetail;
import entity.City;
import entity.Location;
import entity.Order;
import entity.OrderDetail;
import entity.Province;

public interface OrderDao {

	List<CarDetail> searchAdd(@Param("ids") String ids);

	List<Province> searchProvince();

	List<Area> searchArea(@Param("city") City city);

	List<City> searchCity(@Param("prov") Province prov);

	int addLocation(@Param("lo") Location location);

	List<Location> searchLocation(@Param("u_id") int u_id);

	int addOrder(Order order);

	int addOrderDetail(@Param("od") OrderDetail od,@Param("or_id") int or_id,@Param("pr_id") int pr_id);
	
	List<Order> searchOrderDetail(@Param("u_id") int u_id);
	
	int deleteCarDetail(@Param("ids") String cdIds);

	int deleteOrder(Order order);

	int deleteOrderDetails(Order order);

	int deleteLocation(Location location);
	

}
