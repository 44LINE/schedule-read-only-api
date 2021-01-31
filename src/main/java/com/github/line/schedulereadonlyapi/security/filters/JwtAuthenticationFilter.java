package com.github.line.schedulereadonlyapi.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.github.line.schedulereadonlyapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final String jwtSecret;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   String jwtSecret,
                                   UserDetailsService userService,
                                   TokenService tokenService) {
        super(authenticationManager);
        this.jwtSecret = jwtSecret;
        this.userDetailsService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Optional<String> token = getAuthenticationToken(request);
        if (token.isPresent()) {
            Optional<UsernamePasswordAuthenticationToken> authentication = getAuthentication(token.get());
            if (authentication.isPresent()) {
                SecurityContextHolder.getContext().setAuthentication(authentication.get());
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                System.out.println("Authentication failed");
            }
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.out.println("No token found");
        }

        chain.doFilter(request, response);
    }

    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(String token) throws TokenExpiredException {
        if (token.startsWith(TOKEN_PREFIX)) {
            Optional<String> username = decodeUsername(token);
            if (username.isPresent() && tokenService.isAvailable(token.replace(TOKEN_PREFIX, ""))) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username.get());
                return Optional.of(
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities())
                );
            }
        }
        return Optional.empty();
    }

    private Optional<String> getAuthenticationToken(HttpServletRequest request) {
        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
        return token.map(s -> s.replace("\"", ""));
    }

    private Optional<String> decodeUsername(String token) {
        try {
            String userName = JWT.require(Algorithm.HMAC256(jwtSecret))
                                    .build()
                                    .verify(token.replace(TOKEN_PREFIX, ""))
                                    .getSubject();
            return Optional.of(userName);
        } catch (TokenExpiredException e) {
            tokenService.removeToken(token);
            return Optional.empty();
        }
    }
}
