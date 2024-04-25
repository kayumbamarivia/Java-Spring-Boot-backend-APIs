package com.jmv.studentManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmv.studentManagement.model.Student;
import com.jmv.studentManagement.repository.StudentRepository;
import com.jmv.studentManagement.service.StudentService;

//@Controller
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
	private StudentService studentService;
	private StudentRepository studentRepository;
    

	public StudentController(StudentService studentService, StudentRepository studentRepository) {
		super();
		this.studentService = studentService;
		this.studentRepository = studentRepository;
	}


	//	REST API TO CREATE A RESOURCE(STUDENT)

	//	@GetMapping("/signup")
	//	public String signup() {
	//		return "signup";
	//	}
	//
	//	@PostMapping("/signup")
	//	public String saveStudent(@ModelAttribute Student st) {
	//		studentService.saveStudent(st);
	//		return "redirect:/api/students/home";
	//	}

	@PostMapping("/student/{userId}/add")
	public ResponseEntity<Student> saveStudent(@RequestBody Student st, @PathVariable("userId") long userId) {
	    return new ResponseEntity<Student>(studentService.saveStudentByUserId(st, userId), HttpStatus.CREATED);
	}


	//	REST API TO GET ALL RESOURCES(STUDENTS)

	//	@GetMapping("/home")
	//	public String home(Model model) {
	//		List<Student> students = studentService.getAllStudents();
	//		model.addAttribute("students",students);
	//		return "index";
	//	}

	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	@GetMapping("/{userId}/students")
	public List<Student> getAllStudentsByUserId(@PathVariable("userId") long userId){
		return studentService.getStudentsByUserId(userId);
	}


	//	REST API TO GET A RESOURCE(STUDENT)

	//	@GetMapping("/home/{id}")
	//	public String getStudentById(Model model,  @PathVariable("id") long id) {
	//		Student st = studentService.getStudentById(id);
	//		model.addAttribute("student", st);
	//		return "student";
	//	}

	@GetMapping("/student/{id}/get")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id){
		return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
	}

	//	REST API TO EDIT A RESOURCE(STUDENT)	  

	//	@GetMapping("/home/{id}/edit")
	//	public String updateStudentForm(Model model,  @PathVariable("id") long id) {
	//		Student st = studentService.getStudentById(id);
	//		model.addAttribute("student", st);
	//		return "editStudent";
	//	}

	//	@PostMapping("/home/{id}/edit")
	//	public String updateStudent(@ModelAttribute Student st, @PathVariable("id") long id) {
	//		studentService.updateStudentById(st, id);
	//		return "redirect:/api/students/home";
	//	}

	@PutMapping("/student/{id}/edit")
	public ResponseEntity<Student> updateStudentById(@RequestBody Student st,@PathVariable("id") long id){
		return new ResponseEntity<Student>(studentService.updateStudentById(st,id), HttpStatus.OK);
	}

	//	REST API TO DELETE A RESOURCE(STUDENT)

	//	@GetMapping("home/{id}/delete")
	//	public String deleteStudentById(@PathVariable("id") long id) {
	//		studentService.deleteStudentById(id);
	//		return "redirect:/api/students/home"; 
	//	}

	@DeleteMapping("/student/{id}/delete")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") long id){
		studentService.deleteStudentById(id);
		return new ResponseEntity<String>("Student deleted successfully!!", HttpStatus.OK);
	}

	//		REST API TO SEARCH FOR A RESOURCE(S)	
	
	 @GetMapping("/api/student/search")
	    public List<Student> searchStudents(
	            @RequestParam(required = false) Long userId,
	            @RequestParam String searchTerm) {
	        String currentUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();

	        if ("USER".equals(currentUserRole)) {
	            if (userId != null) {
	                return studentRepository.findByUserIdAndFirstNameContainingOrLastNameContainingOrEmailContaining(
	                        userId, searchTerm, searchTerm, searchTerm);
	            } else {
	                return List.of();
	            }
	        } else {
	            return studentRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(
	                    searchTerm, searchTerm, searchTerm);
	        }
	    }

}
