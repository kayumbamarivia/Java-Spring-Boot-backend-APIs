package com.jmv.studentManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody LoginDto request) {
        User user = userServiceImpl.login(request);
        String token = jwtServiceImpl.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<User> register(@RequestBody RegisterDto request){
        User response = userServiceImpl.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/token")
    public ResponseEntity<String> getYourToken(HttpServletRequest request) {
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
    public ResponseEntity<JsonResponse> deleteUserById(@PathVariable("id") long id) {
        userServiceImpl.deleteUserById(id);
        JsonResponse response = new JsonResponse("User deleted successfully!!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping("/user/{id}/edit")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User updatedUser = userServiceImpl.updateUserById(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
