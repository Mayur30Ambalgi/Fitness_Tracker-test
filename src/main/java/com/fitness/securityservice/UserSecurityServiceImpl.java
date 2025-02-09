package com.fitness.securityservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fitness.model.Role;
import com.fitness.model.User;
import com.fitness.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService{

	@Autowired
	private UserService userService;

	@Override
	public boolean accessUser(Authentication authentication, Long userId) {
		
		String email = getLoggedInUserEmail(authentication);
        User loggedInUser = userService.getUserByEmail(email);

        return loggedInUser.getRole() == Role.ROLE_ADMIN || loggedInUser.getId().equals(userId);
	}
	
	 private String getLoggedInUserEmail(Authentication authentication) {
	        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
	            throw new AccessDeniedException("Unauthorized access");
	        }
	        return ((UserDetails) authentication.getPrincipal()).getUsername();
	    }

}
