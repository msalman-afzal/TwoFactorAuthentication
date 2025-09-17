package com.spring.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.security.token.ReceiptAuthToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ReceiptFilter  extends OncePerRequestFilter{
	
	@Autowired
	AuthenticationManager authentManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

	    System.out.println("check the receipt method");
	    String authToken = request.getHeader("Authorization");
	    System.out.println("authtoken " + authToken);

	    // ✅ Fix here: use authToken as principal
	    ReceiptAuthToken token = new ReceiptAuthToken(authToken, null);
           System.out.println(token);
                           var authResult =       authentManager.authenticate(token);

         if(authResult.isAuthenticated()) {
        	 filterChain.doFilter(request, response);
         }
      
}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/login");
	}
	
	

}
