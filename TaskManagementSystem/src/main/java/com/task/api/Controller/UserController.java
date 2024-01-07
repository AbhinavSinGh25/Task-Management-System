package com.task.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.task.api.Model.UserData;
import com.task.api.Repository.UserManagement_Repo;


@RestController
@RequestMapping("/tms")
public class UserController {

	@Autowired
	UserManagement_Repo userManagement_Repo;
	
	@PostMapping("/signup")
	public ResponseEntity<Object> createTask(@RequestBody UserData userData) {
	    try {
	        // Check if the email already exists in the database
	        Optional<UserData> existingUser = userManagement_Repo.findByEmail(userData.getEmail());
	        
	        if (existingUser.isPresent()) {
	            // Email already exists, return an error response
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
	        } else {
	            // Email is unique, proceed to save the user
	            userManagement_Repo.save(userData);
	            return ResponseEntity.status(HttpStatus.OK).header("Location", "/tms/login").build();
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
	    }
	}


	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserData userData) {
	    String email = userData.getEmail();
	    String password = userData.getPassword();

	    // Fetch user by email
	    Optional<UserData> userOptional = userManagement_Repo.findByEmail(email);

	    // Check if the user exists
	    if (userOptional.isPresent()) {
	        UserData user = userOptional.get();
	        if (password.equalsIgnoreCase(user.getPassword())) {
	            // Passwords match, login successful
	            return ResponseEntity.ok("Login successful!");
	        } else {
	            // Passwords don't match, invalid credentials
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    } else {
	        // User not found, invalid credentials
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
	}

	
}
