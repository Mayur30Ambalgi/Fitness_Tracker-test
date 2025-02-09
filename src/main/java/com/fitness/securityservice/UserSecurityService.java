package com.fitness.securityservice;

import org.springframework.security.core.Authentication;

public interface UserSecurityService {
	
	boolean accessUser(Authentication authentication, Long userId);

}
