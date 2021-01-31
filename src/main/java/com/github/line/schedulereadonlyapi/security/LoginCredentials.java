package com.github.line.schedulereadonlyapi.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginCredentials {
    private String username;
    private String password;
}
