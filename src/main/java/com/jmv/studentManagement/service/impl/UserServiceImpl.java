package com.jmv.studentManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jmv.studentManagement.exception.ResourceNotFoundException;
import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;
import com.jmv.studentManagement.model.User;
import com.jmv.studentManagement.repository.UserRepository;
import com.jmv.studentManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	private final JwtServiceImpl jwtServiceImpl;
	private final AuthenticationManager authenticationManager;


	public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder, JwtServiceImpl jwtServiceImpl,
			AuthenticationManager authenticationManager) {
		super();
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
		this.jwtServiceImpl = jwtServiceImpl;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public  Map<String, Object> login(LoginDto request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		User user = repo.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtServiceImpl.generateToken(user);

		Map<String, Object> response = new HashMap<>();
		response.put("user", user);
		response.put("token", token);

		return response;
	}

	@Override
	public User register(RegisterDto request) {
		User user = new User();
		user.setName(request.getName());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		String avatar = request.getAvatar();
		if (avatar == null || avatar.isEmpty()) {
			user.setAvatar("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
		} else {
			user.setAvatar(avatar);
		}
		user.setRole(request.getRole());
		user = repo.save(user);
		return user;
	}


	@Override
	public User getUserById(long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public void deleteUserById(long id) {
		repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		repo.deleteById(id);
	}

	@Override
	public User updateUserById(User u, long id) {
	    User existingOne = repo.findById(id)
	                           .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

	    String name = u.getName();
	    if (name != null && !name.isEmpty()) {
	        existingOne.setName(name);
	    }

	    String username = u.getUsername();
	    if (username != null && !username.isEmpty()) {
	        existingOne.setUsername(username);
	    }

	    String pass = u.getPassword();
	    if (pass != null && !pass.isEmpty()) {
	        existingOne.setPassword(passwordEncoder.encode(pass));
	    }

	    String avatar = u.getAvatar();
	    if (avatar != null && !avatar.isEmpty()) {
	        existingOne.setAvatar(avatar);
	    }
	    
	    return repo.save(existingOne);
	}
}
