package com.jmv.studentManagement.service;

import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;

public interface UserService {
    String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
}
