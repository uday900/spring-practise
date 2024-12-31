package com.darla.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.darla.modal.Student;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {

//	Student student;
	List<Student> students =new ArrayList<>(List.of(
			new Student(101, "uday"),
			 new Student(102, "Vishnu"),
			    new Student(103, "Ravi")));
	
	
	
	@GetMapping("/session-details")
	public String getMethodName(HttpServletRequest req) {
		return "session id is " + req.getSession().getId();
	}
	
	@GetMapping("/test/students")
	public List<Student> getMethodName() {
		return students;
	}
	@GetMapping("/test/token")
	public CsrfToken token(HttpServletRequest req) {
		return (CsrfToken) req.getAttribute("_csrf");
	}
	
	@PostMapping("/test/students")
	public void postMethodName(@RequestBody Student student) {
		//TODO: process POST request
		students.add(student);
	}
	
	
	
}
