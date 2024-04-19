package com.jmv.studentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmv.studentManagement.model.User;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
	Boolean existsByEmail(String email);
}