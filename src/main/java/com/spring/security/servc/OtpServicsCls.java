package com.spring.security.servc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.EntityCls.UserSecrectkey;
import com.spring.security.userRepo.UserOtpRepo;

@Service
public class OtpServicsCls implements UserDetailsService{
	
	@Autowired 
	public UserOtpRepo otpRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<UserSecrectkey> optionalUser = otpRepo.findByUsername(username);

	    return optionalUser.orElseThrow(() ->
	        new UsernameNotFoundException("User not found with username: " + username));
	}


	
	
	

}
