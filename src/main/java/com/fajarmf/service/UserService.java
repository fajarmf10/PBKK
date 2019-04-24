package com.fajarmf.service;

import java.util.List;

import com.fajarmf.model.User;

public interface UserService {
	List<User> getAllUsers();
	User getUser(Integer userid);
	void createUser(Integer userid, String username);
	void updateUser(Integer userid, String username);
	void deleteUser(Integer userid);
	void createUser(String username, String password, Integer usertype);
}
