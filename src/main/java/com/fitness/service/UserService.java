package com.fitness.service;

import com.fitness.model.User;

public interface  UserService {
	
	User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
	

}
