package com.demo.service;

import com.demo.model.User;

import java.util.List;

public interface UserService {

	int insertUser(User user);

	List<User> selectDemo();

}
