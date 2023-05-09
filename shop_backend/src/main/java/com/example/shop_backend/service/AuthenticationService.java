package com.example.shop_backend.service;

import com.example.shop_backend.config.JwtService;
import com.example.shop_backend.domain.dto.AccountLoginDTO;
import com.example.shop_backend.domain.dto.AccountRegisterDTO;
import com.example.shop_backend.domain.dto.AccountTokenResponseDTO;
import com.example.shop_backend.domain.model.user.Role;
import com.example.shop_backend.domain.model.user.User;
import com.example.shop_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AccountTokenResponseDTO register(AccountRegisterDTO request) {
        if(repository.existsByEmail(request.getEmail())) {
            return AccountTokenResponseDTO
                    .builder()
                    .build();
        }
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AccountTokenResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }

    public AccountTokenResponseDTO authenticate(AccountLoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AccountTokenResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }
}
