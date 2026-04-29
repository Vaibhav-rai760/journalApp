package com.vaibhavspringboot.journalApp.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vaibhavspringboot.journalApp.Entity.JournalEntry;
import com.vaibhavspringboot.journalApp.Entity.User;
import com.vaibhavspringboot.journalApp.repository.JournalEntryRepository;
import com.vaibhavspringboot.journalApp.repository.UserentryRepository;


@Service
@Component
public class UserEntryService {

	@Autowired
	private  UserentryRepository userentryRepository;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private static  final Logger logger = LoggerFactory.getLogger(UserEntryService.class);
	
	public boolean saveNewUser(User user) {
		
		try {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userentryRepository.save(user);
		return true;
		} catch (Exception e) {
			logger.info("hahahahahahh");
			return false;
		}
		
	}
	
	public void saveUser(User  user)
	{
		  userentryRepository.save(user);
	}
	
	
	
	public List<User> getall(){
		return userentryRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id){
		return userentryRepository.findById(id);
	}

	public void deleteById(ObjectId myId) {
		// TODO Auto-generated method stub
		userentryRepository.deleteById(myId);
	}
	
	public User findByUserName(String userName)
	{
		return userentryRepository.findByUserName(userName);
	}

	public void saveAdmin(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userentryRepository.save(user);
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
