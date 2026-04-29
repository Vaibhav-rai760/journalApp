package com.vaibhavspringboot.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavspringboot.journalApp.Entity.User;
import com.vaibhavspringboot.journalApp.Service.UserEntryService;

@RestController
@RequestMapping("/public")
public class PublicController {
  
	
	@Autowired
	private UserEntryService userservice;
	
	@GetMapping("/health-check")
	public String healthcheck()
	{
		return "ok";
	}
	@PostMapping("/create-user")
	public void createUser(@RequestBody User user) {
		userservice.saveNewUser(user);
	}
}
