package com.jmv.studentManagement.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jmv.studentManagement.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository repo;
	
	public UserDetailsServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User with "+username+" is not found"));
	}

}
