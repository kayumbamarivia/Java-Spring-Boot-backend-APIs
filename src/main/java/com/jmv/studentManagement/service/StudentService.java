 package com.jmv.studentManagement.service;

import java.util.List;

import com.jmv.studentManagement.model.Student;

public interface StudentService {
	Student saveStudentByUserId(Student st, long id);
	List<Student> getAllStudents();
	Student getStudentById(long id);
	List<Student> getStudentsByUserId(long id);
	Student updateStudentById(Student st, long id);
	void deleteStudentById(long id);
	List<Student> search(String query);
	List<Student> searchByUserId(String query, long userId);
}
