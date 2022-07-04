package com.greatlearning.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.library.entity.User;
import com.greatlearning.library.repositories.UserRepository;
import com.greatlearning.library.security.BookUserDetails;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails getUserByUsername(String username) {
		User user=userRepo.getUserByUserName(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User "+username+" not found!!!");
		}
		
		UserDetails bookUserDetails=new BookUserDetails(user);
		return bookUserDetails;
	}

}
