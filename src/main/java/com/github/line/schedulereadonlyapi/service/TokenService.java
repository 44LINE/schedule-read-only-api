package com.github.line.schedulereadonlyapi.service;

import com.github.line.schedulereadonlyapi.repository.ConfirmationTokenRepository;
import com.github.line.schedulereadonlyapi.domain.JWToken;
import com.github.line.schedulereadonlyapi.repository.JWTokenRepository;
import com.github.line.schedulereadonlyapi.security.SecurityContextExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JWTokenRepository jwTokenRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public boolean isAvailable(String token) {
        Optional<JWToken> jwToken = jwTokenRepository.findById(token);
        return jwToken.isPresent() && jwToken.get().getToken().equals(token);
    }

    @Transactional
    public String saveAuthToken(String token, String username) {
        System.out.println("saved");
        return jwTokenRepository.save(new JWToken(token, username)).getToken();
    }

    @Transactional
    public void removeToken(String token) {
        jwTokenRepository.deleteById(token);
    }

    @Transactional
    public void removeAuthTokens() {
        Optional<String> username = SecurityContextExtractor.getCurrentUser();
        if (username.isPresent()) {
            jwTokenRepository.deleteByUsername(username.get());
        }
    }
}
