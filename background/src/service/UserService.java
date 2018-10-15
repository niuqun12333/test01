package service;

import java.util.List;

import entity.User;

public interface UserService {

	User search(User user);

	List<User> searchAllUesr(User user);

}
