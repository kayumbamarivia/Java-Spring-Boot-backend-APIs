package com.jmv.studentManagement.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmv.studentManagement.exception.ResourceNotFoundException;
import com.jmv.studentManagement.model.Student;
import com.jmv.studentManagement.model.User;
import com.jmv.studentManagement.repository.StudentRepository;
import com.jmv.studentManagement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student st) {
		return studentRepository.save(st);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		//		Optional<Student> student = studentRepository.findById(id);
		//		if (student.isPresent()) {
		//			return student.get();
		//		} else {
		//			throw new ResourceNotFoundException("Student", "id", id);
		//		}
		return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
	}

	@Override
	public Student updateStudentById(Student st, long id) {
		Student exixtingOne = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		exixtingOne.setFirstName(st.getFirstName());
		exixtingOne.setLastName(st.getLastName());
		exixtingOne.setEmail(st.getEmail());
		studentRepository.save(exixtingOne);
		return exixtingOne;
	}

	@Override
	public void deleteStudentById(long id) {
		studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> Search(String query) {
		return studentRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(query, query, query);
	}

	@Override
	public Student getStudentByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudentByUserId(User u, long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
