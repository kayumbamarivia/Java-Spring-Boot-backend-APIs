package com.jmv.studentManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;
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
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
    	String response = userService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = userService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}