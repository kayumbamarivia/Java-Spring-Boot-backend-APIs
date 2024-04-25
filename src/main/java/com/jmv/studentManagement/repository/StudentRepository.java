package com.jmv.studentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmv.studentManagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String query, String query2, String query3);
	List<Student> findByUserIdAndFirstNameContainingOrUserIdAndLastNameContainingOrUserIdAndEmailContaining(
			long userId1, String firstName, 
			long userId2, String lastName, 
			long userId3, String email
			);

	List<Student> findByUserId(long userId);
}
