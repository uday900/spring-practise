package com.darla.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.darla.modal.User;
import com.darla.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	BCryptPasswordEncoder encode = new BCryptPasswordEncoder(12);
	
	
	public List<User> getUsers(){
		return repo.findAll();
	}
	public void postUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(encode.encode(user.getPassword()));
		repo.save(user);
	}
	
	public String validateUser(User user) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
//		return authentication.isAuthenticated() ? "valid" : "invalid";
		if ( authentication.isAuthenticated() ) {
			
			return jwtService.generateJWTToken(user.getUserName());
		}
		return "fail";
	
	}

}
