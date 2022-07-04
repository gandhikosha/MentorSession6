package com.greatlearning.library.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	
	public UserDetails getUserByUsername(String username);

}
