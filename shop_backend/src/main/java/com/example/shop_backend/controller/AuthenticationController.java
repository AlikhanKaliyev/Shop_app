package com.example.shop_backend.controller;

import com.example.shop_backend.domain.dto.AccountLoginDTO;
import com.example.shop_backend.domain.dto.AccountRegisterDTO;
import com.example.shop_backend.domain.dto.AccountTokenResponseDTO;
import com.example.shop_backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AccountTokenResponseDTO> register(
            @RequestBody AccountRegisterDTO request
    ) {
        AccountTokenResponseDTO result = service.register(request);
        if(result.getToken() == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AccountTokenResponseDTO> authenticate(
            @RequestBody AccountLoginDTO request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
