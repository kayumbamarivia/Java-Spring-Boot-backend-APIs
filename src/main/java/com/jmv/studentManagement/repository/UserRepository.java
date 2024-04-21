package com.jmv.studentManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmv.studentManagement.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}