package com.jmv.studentManagement.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmv.studentManagement.model.LoginDto;
import com.jmv.studentManagement.model.RegisterDto;
import com.jmv.studentManagement.model.User;
import com.jmv.studentManagement.service.impl.JwtServiceImpl;
import com.jmv.studentManagement.service.impl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final JwtServiceImpl jwtServiceImpl;

    public AuthController(UserServiceImpl userServiceImpl, JwtServiceImpl jwtServiceImpl) {
        super();
        this.userServiceImpl = userServiceImpl;
        this.jwtServiceImpl = jwtServiceImpl;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<User> authenticate(@RequestBody LoginDto request) {
        User user = userServiceImpl.login(request);
        String token = jwtServiceImpl.generateToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<User> register(@RequestBody RegisterDto request){
        User response = userServiceImpl.register(request);
        return ResponseEntity.ok(response);
    }

    // Logout API
    @GetMapping("/token")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        return ResponseEntity.ok("User token is this : "+token);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }


    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/user/{id}/get")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        return new ResponseEntity<User>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") long id){
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<String>("User deleted successfully!!", HttpStatus.OK);
    }

    // Update User API
    @PutMapping("/user/{id}/edit")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User updatedUser = userServiceImpl.updateUserById(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
