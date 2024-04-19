package com.jmv.studentManagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jmv.studentManagement.exception.ResourceNotFoundException;
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
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (!loginDto.getPassword().equals(user.getPassword())) {
        	throw new ResourceNotFoundException("User", "email", loginDto.getEmail());
            
        }
        return user;
    }

    @Override
    public User register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
        	throw new ResourceNotFoundException("User", "email");
        }
        User user = new User(registerDto.getName(),registerDto.getEmail(),registerDto.getPassword(),registerDto.getRole());
        userRepository.save(user);

        return user;
    }

    @Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUserById(long id) {
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.deleteById(id);
	}
}
