package com.spring.security.appConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring.security.filter.ReceiptFilter;
import com.spring.security.filter.UserAuthFilter;
import com.spring.security.provider.OneTimeAuthProvider;
import com.spring.security.provider.ReceiptAuthProvider;
import com.spring.security.provider.UserAuthProvider;

@Configuration
public class AppCofig {

	@Autowired
	 public UserAuthFilter authFilter;
   
	@Autowired
	@Lazy
	public  UserAuthProvider authProvider;
	
	@Autowired
	public OneTimeAuthProvider authProvider2;
	
	@Autowired
	@Lazy
      ReceiptFilter filter;
	
	@Autowired
	ReceiptAuthProvider authProvider3;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	  
	        .addFilterAt(authFilter, BasicAuthenticationFilter.class) 
	        .addFilterAfter(filter, BasicAuthenticationFilter.class)

	        // پہلے والا فلٹر
// بعد والا فلٹر
	        .httpBasic(); // HTTP Basic Auth enable کریں
	    
	    return http.build();
	}

	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http)throws Exception{
		         AuthenticationManagerBuilder builder =   http.getSharedObject(AuthenticationManagerBuilder.class);
		         System.out.println("run cofig class");
		   builder.authenticationProvider(authProvider)
		   .authenticationProvider(authProvider2)
		   .authenticationProvider(authProvider3);
		return  builder.build();
	}
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	    return config.getAuthenticationManager();
//	}

	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
