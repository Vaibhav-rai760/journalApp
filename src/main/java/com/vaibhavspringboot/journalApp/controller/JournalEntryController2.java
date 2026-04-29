package com.vaibhavspringboot.journalApp.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavspringboot.journalApp.Entity.JournalEntry;
import com.vaibhavspringboot.journalApp.Entity.User;
import com.vaibhavspringboot.journalApp.Service.JournalEntryService;
import com.vaibhavspringboot.journalApp.Service.UserEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController2 {


  
	@Autowired
	private JournalEntryService journalentryservice;
	
	@Autowired
	private UserEntryService userService;
	
	
  @GetMapping
  public ResponseEntity<?> getallJournalEntriesOfUser()
  {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
	  User user =userService.findByUserName(userName);
	  List<JournalEntry> all =  user.getJournalentries();
	  if(all != null && !all.isEmpty()) {
		  return new ResponseEntity<>(all,HttpStatus.OK);
	  }
	  return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  @PostMapping
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry Myentry) {
	  try {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
		  //User user =userService.findByUserName(userName);
		  Myentry.setDate(LocalDateTime.now());
		  journalentryservice.saveEntry(Myentry,userName);	
		  return new ResponseEntity<>(Myentry,HttpStatus.CREATED);
	  }catch (Exception e)
	  {
		  return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	  }
	  
  }
  
  @GetMapping("id/{myId}")
  public ResponseEntity <JournalEntry>  getjournalentrybyid(@PathVariable ObjectId myId)
  {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		   User user = userService.findByUserName(userName);
		   List<JournalEntry> collect =  user.getJournalentries()
				    .stream()
				    .filter(x -> x.getId().equals(myId))
				    .collect(Collectors.toList());
		   
		   if(!collect.isEmpty())
		   {
			   Optional<JournalEntry> journalEntry = journalentryservice.findById(myId);
			   if(journalEntry.isPresent())
				{
					return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
				}
		   }
		
	
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  @DeleteMapping("id/{myId}")
  public ResponseEntity<?> deletejournalentrybyid(@PathVariable ObjectId myId)
  { 
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
	  journalentryservice.deleteById(myId,userName);
	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  
  @PutMapping("id/{myId}")
  public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,@RequestBody JournalEntry Newentry)
  {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		 User user = userService.findByUserName(userName);
		   List<JournalEntry> collect =  user.getJournalentries()
				    .stream()
				    .filter(x -> x.getId().equals(myId))
				    .collect(Collectors.toList());
		   
		   if(!collect.isEmpty())
		   {
			   Optional<JournalEntry> journalEntry = journalentryservice.findById(myId);
			   if(journalEntry.isPresent())
				{JournalEntry old = journalEntry.get();
				   old.setTitle(Newentry.getTitle()!= null && !Newentry.getTitle().equals("")?Newentry.getTitle():old.getTitle());
					  old.setContent(Newentry.getContent()!=null && !Newentry.equals("") ? Newentry.getContent() : old.getContent());
				  journalentryservice.saveEntry(old);
				  return new ResponseEntity<>(old,HttpStatus.OK);
				}
		   }
	  
	  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  
  
  
}
