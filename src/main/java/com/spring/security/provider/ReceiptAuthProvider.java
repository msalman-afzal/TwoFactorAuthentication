package com.spring.security.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.security.token.ReceiptAuthToken;
import com.spring.security.userRepo.ReceiptToken;

@Component
public class ReceiptAuthProvider implements AuthenticationProvider{

	@Autowired
	private ReceiptToken receiptToken;

	

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    System.out.println("Calling authenticate with receipt");

	    try {
	        // Step 1: Get receipt number from Authentication object
	        String receiptNo = authentication.getName();

	        // Step 2: Validate receipt number (custom logic)
	        boolean isValid = receiptToken.contain(receiptNo);
	        System.out.println("is valid : " + isValid);// Make sure this method exists and works

	        if (isValid) {
	            // Step 3: If valid, return authenticated token
	            return new ReceiptAuthToken(receiptNo, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
	        } else {
	            // Step 4: If not valid, throw exception
	            throw new BadCredentialsException("Invalid receipt number");
	        }

	    } catch (Exception e) {
	        // Catch all unexpected errors
	        throw new InternalAuthenticationServiceException("Authentication failed due to server error", e);
	    }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		System.out.println("calling the suppport with receipt method ");
		
		return ReceiptAuthToken.class.equals(authentication);
	}

}
