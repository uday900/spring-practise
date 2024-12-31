package com.darla.controllers.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darla.modal.User;
import com.darla.services.UserService;


@RestController
//@RequestMapping("/shop-with-us")
public class UserController {

	@Autowired
	UserService service;
	

	@GetMapping("/users")
	public ResponseEntity<?> getMethodName() {
	
		List<User> users = service.getUsers();
		
		if ( users.size() == 0 ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> postMethodName(@RequestBody User user) {
		//TODO: process POST request
		service.postUser(user);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public String postMethodName2(@RequestBody User user) {
		//TODO: process POST request
		System.out.println(user);
		return service.validateUser(user);
//		return "success";
//				service.validateUser(user);
	}
	
	
}
