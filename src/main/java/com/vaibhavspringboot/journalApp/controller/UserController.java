package com.vaibhavspringboot.journalApp.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavspringboot.journalApp.Entity.User;
import com.vaibhavspringboot.journalApp.Service.UserEntryService;
import com.vaibhavspringboot.journalApp.repository.UserentryRepository;

@RestController
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	private UserEntryService userservice;
	
	@Autowired
	private  UserentryRepository userentryRepository;
	
//	
//	@GetMapping
//	public List<User> getallUsers(){
//		 return userservice.getall();
//	}
//	
//	@PostMapping
//	public void createUser(@RequestBody User user) {
//		userservice.saveNewUser(user);
//	}
	
	@PutMapping
	public  ResponseEntity<?> updateUser(@RequestBody User user)
	{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User userInDb = userservice.findByUserName(userName);
		if(userInDb !=null)
		{
			userInDb.setUserName(user.getUserName());
			userInDb.setPassword(user.getPassword());
			userservice.saveNewUser(userInDb);
			
		}
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@DeleteMapping
	 public ResponseEntity<?> deleteUserbyID()
	 {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userentryRepository.deleteByUserName(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }
	 
}
