package com.spring.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.security.servc.UserviesClass;
import com.spring.security.token.UserAuthToken;

@Component
public class UserAuthProvider  implements AuthenticationProvider{
	
	@Autowired
	 public  UserviesClass userviesClass;
	
	@Autowired
	public PasswordEncoder  encoder;
	

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		System.out.println("calling the fourth class  ::  ");
		System.out.println();
		
		        UserDetails u =  userviesClass.loadUserByUsername(auth.getName());
	
	if(encoder.matches(auth.getCredentials().toString(), u.getPassword().toString())) {
		 return new UserAuthToken(auth.getPrincipal(), u.getPassword(), auth.getAuthorities());
	}
		        
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		System.out.println("calling the third Class support method  ::  ");
		System.out.println();
		
		return UserAuthToken.class.equals(authentication);
	}
	

}
