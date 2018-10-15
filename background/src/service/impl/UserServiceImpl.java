package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	@Override
	public User search(User user) {
		User us= userDao.search(user);
		return us;
	}
	@Override
	public List<User> searchAllUesr(User user) {
		List<User> list = userDao.searchAllUesr(user);
		return list;
	}

}
