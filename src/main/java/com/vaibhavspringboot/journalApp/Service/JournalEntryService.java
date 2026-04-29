package com.vaibhavspringboot.journalApp.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vaibhavspringboot.journalApp.Entity.JournalEntry;
import com.vaibhavspringboot.journalApp.Entity.User;
import com.vaibhavspringboot.journalApp.repository.JournalEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@Component
public class JournalEntryService {

	@Autowired
	private  JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserEntryService  userService;
	
	
	
	
	@Transactional
	public void saveEntry(JournalEntry  journalEntry, String userName)
	{
		 User user =  userService.findByUserName(userName);
		 journalEntry.setDate(LocalDateTime.now());
		 JournalEntry saved = journalEntryRepository.save(journalEntry);
		 user.getJournalentries().add(saved);
		 userService.saveUser(user);
	}
	
	
	public void saveEntry(JournalEntry  journalEntry)
	{
		journalEntryRepository.save(journalEntry);
	}
	
	public List<JournalEntry> getall(){
		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id){
		return journalEntryRepository.findById(id);
	}

	@Transactional
	public void deleteById(ObjectId myId,String userName) {
		// TODO Auto-generated method stub
		try {
		 User user =  userService.findByUserName(userName);
		 boolean removed = user.getJournalentries().removeIf(x -> x.getId().equals(myId));
		 if(removed) {
			 userService.saveUser(user);
				journalEntryRepository.deleteById(myId); 
		 }
		}
		catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("An error occured while  deleting  the entry ",e);
		}
		 
	}
	
	public List<JournalEntry> findbyUserName(String userName)
	{
		return null;
		
	}
	
//	public void deleteById(ObjectId Id)
//	{
//		 journalEntryRepository.deleteById(Id);
//	}

//	public void deleteById1(ObjectId myId) {
//		// TODO Auto-generated method stub
//		journalEntryRepository.deleteById(myId);
//	}
}
