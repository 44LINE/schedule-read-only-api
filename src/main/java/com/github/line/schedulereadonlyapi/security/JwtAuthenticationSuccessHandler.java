package com.github.line.schedulereadonlyapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.line.schedulereadonlyapi.service.TokenService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final long jwtExpiration;
    private final String jwtSecret;
    private final TokenService tokenService;

    public JwtAuthenticationSuccessHandler(@Value("${jwt.expiration}") long jwtExpiration,
                                           @Value(("${jwt.secret}")) String jwtSecret,
                                           @Autowired TokenService tokenService) {
        this.jwtExpiration = jwtExpiration;
        this.jwtSecret = jwtSecret;
        this.tokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String principal = (String) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(principal)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                .sign(Algorithm.HMAC256(jwtSecret));
        tokenService.saveAuthToken(token, principal);
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
