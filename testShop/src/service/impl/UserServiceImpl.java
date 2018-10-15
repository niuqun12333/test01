package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User search(User user) {
		User us= userDao.search(user);
		return us;
	}

	@Override
	public boolean add(User user) {
		int rs = userDao.add(user);
		int u_id = user.getId();
		user.setId(u_id);
		rs = userDao.addCar(user);
		return rs>0;
	}



}
