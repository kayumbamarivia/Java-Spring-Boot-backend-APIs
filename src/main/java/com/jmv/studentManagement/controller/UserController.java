package com.jmv.studentManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;
import com.jmv.studentManagement.model.User;
import com.jmv.studentManagement.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto){
    	return ResponseEntity.ok(userService.login(loginDto));
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
    	return new ResponseEntity<User>(userService.register(registerDto), HttpStatus.CREATED);
    }
    
    @GetMapping()
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
    
    @DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") long id){
		userService.deleteUserById(id);
		return new ResponseEntity<String>("User deleted successfully!!", HttpStatus.OK);
	}
}