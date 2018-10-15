package dao;

import org.apache.ibatis.annotations.Param;

import entity.Car;
import entity.CarDetail;
import entity.Product;
import entity.User;

public interface CarDao {

	User searchCar(@Param("user") User condition);
	
	int searchCarId(@Param("u_id") int id);

	int addCar(@Param("pro") Product pro, @Param("number") int num, @Param("c_id") int c_id);

	int updateCar(@Param("pro") Product pro, @Param("number") int num, @Param("c_id") int c_id);

	int deleteCar(@Param("cd") CarDetail cd);

	int searchCarDetail(@Param("pro") Product pro, @Param("c_id") int c_id);

	int updateCarDetail(@Param("cd") CarDetail cd, @Param("number") int number);
	
	int updateCarAll(@Param("car") Car car);

	int deleteCarDetails(@Param("ids") String cdIds);
	
}
