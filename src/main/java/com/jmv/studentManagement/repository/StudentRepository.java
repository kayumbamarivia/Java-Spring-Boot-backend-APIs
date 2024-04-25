package com.jmv.studentManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jmv.studentManagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByFirstNameContainingOrLastNameContainingOrEmailContaining(String query, String query2, String query3);
	List<Student> findByUserId(long userId);
	@Query("SELECT s FROM Student s WHERE s.userId = :userId AND (s.firstName LIKE %:searchTerm% OR s.lastName LIKE %:searchTerm% OR s.email LIKE %:searchTerm%)")
    List<Student> findByUserIdAndSearchTerm(Long userId, String searchTerm);
}
