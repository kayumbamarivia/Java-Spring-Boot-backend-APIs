package com.jmv.studentManagement.service;

import java.util.List;

import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;
import com.jmv.studentManagement.model.User;

public interface UserService {
    User login(LoginDto loginDto);
	User register(RegisterDto registerDto);
	List<User> getAllUsers();
	void deleteUserById(long id);
	User getUserById(long id);
	User updateUserById(User u, long id);
}
