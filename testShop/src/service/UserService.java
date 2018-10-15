package service;

import entity.User;

public interface UserService {

	User search(User user);

	boolean add(User user);

	
}

