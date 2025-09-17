package com.spring.security.controlar;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HomeControllar {
	
	@GetMapping("/twoFactorAuth")
	public String hello() {
	String str = "hello I am using the two factor Authentication";
	return str;
	}

}
