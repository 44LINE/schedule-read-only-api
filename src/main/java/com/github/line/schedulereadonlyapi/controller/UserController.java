package com.github.line.schedulereadonlyapi.controller;

import com.github.line.schedulereadonlyapi.domain.User;
import com.github.line.schedulereadonlyapi.security.SignUpRequest;
import com.github.line.schedulereadonlyapi.service.TokenService;
import com.github.line.schedulereadonlyapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @GetMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    User getUser() {
        return userService.getUser().orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    User register(@RequestBody SignUpRequest signUpRequest) {
        return userService.saveUser(signUpRequest);
    }

    @DeleteMapping(value = "/signout")
    @ResponseStatus(HttpStatus.OK)
    void logout() {
        tokenService.removeAuthTokens();
    }
}
