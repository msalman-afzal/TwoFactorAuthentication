package com.spring.security.servc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.EntityCls.UserEntity;
import com.spring.security.userRepo.UserRepo;


@Service
public class UserviesClass implements UserDetailsService {
 
	
	@Autowired
	public UserRepo repo;
	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("calling the Services class  ");
		System.out.println();
		
		UserEntity u =	    repo.findByUsername(username).orElse(new UserEntity());
		return u;
	}
	
	
}
