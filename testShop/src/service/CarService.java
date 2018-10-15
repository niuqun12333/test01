package service;

import entity.Car;
import entity.CarDetail;
import entity.Product;
import entity.User;

public interface CarService {

	User searchCar(User condition);
	
	boolean addCar(Product pro, int id, int num, int c_id);

	boolean deleteCar(CarDetail cd);

	int searchCarId(int u_id);

	boolean updateCarDetail(CarDetail cd, int number);

	boolean updateCarAll(Car car);

	boolean deleteCarDetails(String cdIds);

	
}
