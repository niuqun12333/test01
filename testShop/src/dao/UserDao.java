package dao;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserDao {

	User search(@Param("user") User user);

	int add(User user);
	
	int addCar(User user);
	
	
}
