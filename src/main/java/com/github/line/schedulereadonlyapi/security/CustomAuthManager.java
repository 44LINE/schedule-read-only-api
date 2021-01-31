package com.github.line.schedulereadonlyapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthManager implements AuthenticationManager {
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        if (userDetails != null && BCrypt.checkpw((String) authentication.getCredentials(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(principal, null, userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Bad credentials");
        }

    }
}
