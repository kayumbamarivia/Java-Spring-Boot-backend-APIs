 package com.jmv.studentManagement.service;

import java.util.List;

import com.jmv.studentManagement.model.Student;
import com.jmv.studentManagement.model.User;

public interface StudentService {
	Student saveStudent(Student st);
	List<Student> getAllStudents();
	Student getStudentById(long id);
	Student getStudentByUserId(long id);
	Student updateStudentById(Student st, long id);
	Student updateStudentByUserId(User u, long id);
	void deleteStudentById(long id);
	List<Student> Search(String query);
}
