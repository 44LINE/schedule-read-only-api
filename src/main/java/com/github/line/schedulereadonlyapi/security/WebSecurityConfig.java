package com.github.line.schedulereadonlyapi.security;

import com.github.line.schedulereadonlyapi.security.filters.JsonCredentialsAuthenticationFilter;
import com.github.line.schedulereadonlyapi.security.filters.JwtAuthenticationFilter;
import com.github.line.schedulereadonlyapi.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    private final JwtAuthenticationSuccessHandler successHandler;
    private final JwtAuthenticationFailureHandler failureHandler;
    private final String jwtSecret;
    private final TokenService tokenService;
    private final UserDetailsService userDetailsService;
    private final CustomAuthManager authenticationManager;

    public WebSecurityConfig(JwtAuthenticationSuccessHandler successHandler,
                             JwtAuthenticationFailureHandler failureHandler,
                             @Value(("${jwt.secret}")) String jwtSecret,
                             TokenService tokenService,
                             UserDetailsService userService, CustomAuthManager authenticationManager1) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.jwtSecret = jwtSecret;
        this.tokenService = tokenService;
        this.userDetailsService = userService;
        this.authenticationManager = authenticationManager1;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/")
                    .permitAll()
                .antMatchers("/login", "/signup")
                    .anonymous()
                .antMatchers("/api/**", "/user/**", "/signout")
                    .authenticated()
                .anyRequest()
                    .authenticated()
                    .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtSecret, userDetailsService, tokenService))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Bean
    public JsonCredentialsAuthenticationFilter authenticationFilter() throws Exception {
        JsonCredentialsAuthenticationFilter authenticationFilter = new JsonCredentialsAuthenticationFilter(userDetailsService);
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        return authenticationFilter;
    }

    /*
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(ENCODER);
        return authProvider;
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(ENCODER)
                .and()
                .authenticationProvider(authenticationProvider())
                .build();
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //String password = new BCryptPasswordEncoder().encode("password");
        auth.authenticationProvider(authenticationProvider());
        /*
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{bcrypt}" + password)
                .roles("USER");


    }
    */


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
