package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CarDao;
import entity.Car;
import entity.CarDetail;
import entity.Product;
import entity.User;
import service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarDao carDao;

	@Override
	public User searchCar(User condition) {
		User user = carDao.searchCar(condition);
		return user;
		
	}
	@Override
	public int searchCarId(int u_id) {
		int c_id = carDao.searchCarId(u_id);
		return c_id;
	}

	@Override
	public boolean addCar(Product pro, int id, int num, int c_id) {
		boolean flag = false;
		int rs = carDao.searchCarDetail(pro, c_id);
		if (rs > 0) {
			flag = true;
		}
		if (flag) {
			carDao.updateCar(pro, num, c_id);
		} else {
			carDao.addCar(pro, num, c_id);
		}
		return true;
	}

	@Override
	public boolean deleteCar(CarDetail cd) {
		int rs = carDao.deleteCar(cd);
		return rs > 0;
	}

	@Override
	public boolean updateCarDetail(CarDetail cd, int number) {
		int rs = carDao.updateCarDetail(cd, number);
		return rs > 0;
	}

	@Override
	public boolean updateCarAll(Car car) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteCarDetails(String cdIds) {
		int rs = carDao.deleteCarDetails(cdIds);
		return rs>0;
	}

}
