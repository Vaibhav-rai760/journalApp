package com.vaibhavspringboot.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaibhavspringboot.journalApp.Entity.User;

public interface UserentryRepository extends MongoRepository<User,ObjectId>{
	
	User findByUserName(String userName);

	void deleteByUserName(String userName);

}
