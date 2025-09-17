package com.spring.security.userRepo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("singleton")  // explicitly singleton scope

public class ReceiptToken {
    private static final Set<String> token = new HashSet<>();

    public void add(String value) {
    	System.out.println("calling add method + " + value);
        System.out.println("Current Token Set (ADD): " + token);
        token.add(value);
    }

    public boolean contain(String value) {
    	   System.out.println("CHECKING IF TOKEN EXISTS: " + value);
           System.out.println("Current Token Set (CHECK): " + token);
        return token.contains(value);
    }
}