package com.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fitness.exceptionhandler.ResourceNotFoundException;
import com.fitness.model.User;
import com.fitness.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
