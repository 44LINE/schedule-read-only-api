package com.github.line.schedulereadonlyapi.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Setter
public class SignUpRequest {
    @Getter private String username;
    private String password;
    @Getter private int groupId;

    public String getPassword() {
        System.out.println(WebSecurityConfig.ENCODER.encode(password));
        return WebSecurityConfig.ENCODER.encode(password);
    }
}
