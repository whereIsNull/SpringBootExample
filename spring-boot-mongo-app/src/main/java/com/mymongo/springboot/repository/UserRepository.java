package com.mymongo.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mymongo.springboot.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	
}
