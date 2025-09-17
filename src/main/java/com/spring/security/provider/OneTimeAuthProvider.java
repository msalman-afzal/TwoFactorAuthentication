package com.spring.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.security.EntityCls.UserSecrectkey;
import com.spring.security.servc.OtpServicsCls;
import com.spring.security.token.OneTimePassAuthToken;

@Component
public class OneTimeAuthProvider implements AuthenticationProvider {

    @Autowired
    private OtpServicsCls otpServicsCls;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    	
    	System.out.println("Then calling the Authentication method ");
    	System.out.println();
        String username = auth.getName();
        String providedOtp = auth.getCredentials().toString();

        // Load user from DB
        UserSecrectkey user = (UserSecrectkey) otpServicsCls.loadUserByUsername(username);

        String storedOtp = user.getPassword(); // or user.getSecretkey()

        if (storedOtp != null && storedOtp.equals(providedOtp)) {
            return new OneTimePassAuthToken(username, providedOtp, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid OTP provided");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
    	System.out.println("calling the otp support method ");
    	System.out.println();
        return OneTimePassAuthToken.class.isAssignableFrom(authentication);
    }
}
