package com.spring.security.filter;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.EntityCls.UserSecrectkey;
import com.spring.security.token.OneTimePassAuthToken;
import com.spring.security.token.UserAuthToken;
import com.spring.security.userRepo.ReceiptToken;
import com.spring.security.userRepo.UserOtpRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//
@Component
public class UserAuthFilter  extends OncePerRequestFilter{

	   @Autowired
	   @Lazy
	   AuthenticationManager manager;
	   
	   @Autowired
	   public  UserOtpRepo otpRepo;
	   
	   @Autowired
	   private ReceiptToken receiptToken;

	   
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("calling the second class auth Filter ::  ");
		System.out.println(); 
		
		
		String Uname = request.getHeader("username");
		System.out.println(Uname);
		String Upass = request.getHeader("password");
		System.out.println(Upass);
		String oneTimePassword = request.getHeader("oneTimePassword");
		System.out.println(oneTimePassword);
		
		if(Uname != null && Upass != null) {
		
		UserAuthToken obj = new UserAuthToken(Uname, Upass);
		     Authentication tokan =   manager.authenticate(obj);
		     
		     
		     Random key=  new Random();
		       int Keynumber = ( key.nextInt(999) *1000);
		       String otpNumber = Integer.toString(Keynumber);
		       
		       UserSecrectkey obj1 = new UserSecrectkey();
		       obj1.setUsername(Uname);
		       obj1.setSecretkey(otpNumber);
		       otpRepo.save(obj1);
		       
		
		}
		
		
		else if(Uname != null  && oneTimePassword != null ){
			System.out.println("calling the else if logic ");
			 
		 OneTimePassAuthToken otpToken = new OneTimePassAuthToken(Uname, oneTimePassword);
		 Authentication otpObj = manager.authenticate(otpToken);

		// SET authentication in SecurityContext
//		SecurityContextHolder.getContext().setAuthentication(otpObj);
		 String str = UUID.randomUUID().toString();
		 receiptToken.add(str);
            
		// Response header ya token set karo
		response.setHeader("Authorization", str);

			      
			    

		}
		
			 
		else {
			throw new  BadCredentialsException("User and Password is Not Correct ::"
					+ " " + Uname +  ":" + Upass +  ": " + oneTimePassword);
		}	
	}

	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return !request.getServletPath().equals("/login");
	}
	


}
