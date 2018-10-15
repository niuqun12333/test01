package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.User;

public interface UserDao {
	
	User search(@Param("user") User user);

	List<User> searchAllUesr(User user);
}
