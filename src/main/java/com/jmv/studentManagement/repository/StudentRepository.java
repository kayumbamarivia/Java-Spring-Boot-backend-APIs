package com.jmv.studentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmv.studentManagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String query, String query2, String query3);
	List<Student> findByUserIdAndFirstNameContainingOrLastNameContainingOrEmailContaining(long userId, String firstName, String lastName, String email);
	List<Student> findByUserId(long userId);
}
