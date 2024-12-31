package com.darla.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.darla.modal.User;
import com.darla.modal.UserPrinciple;
import com.darla.repository.UserRepository;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	UserRepository repo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username);
		User user = repo.findByUserName(username);
		
		if (user == null ) {
			System.out.println("not found user");
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
		
		return new UserPrinciple(user);
	}

}
