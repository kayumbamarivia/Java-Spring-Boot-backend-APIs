package com.jmv.studentManagement.service.impl;

import org.springframework.stereotype.Service;
import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;
import com.jmv.studentManagement.model.User;
import com.jmv.studentManagement.repository.UserRepository;
import com.jmv.studentManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "User with that email already exists";
            
        }
        return "User logged in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
        	return "User with that email already exists";
        }
        User user = new User(registerDto.getName(),registerDto.getEmail(),registerDto.getPassword(),registerDto.getRole());
        userRepository.save(user);

        return "User registration successful.";
    }
}
