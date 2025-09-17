package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwoFactorAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoFactorAuthenticationApplication.class, args);
		System.out.println("calling the first class ::  ");
		System.out.println();
	}

}
