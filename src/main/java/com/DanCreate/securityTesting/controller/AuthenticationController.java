package com.DanCreate.securityTesting.controller;

import com.DanCreate.securityTesting.model.dtos.AuthenticateDtos;
import com.DanCreate.securityTesting.model.dtos.RegisterDtos;
import com.DanCreate.securityTesting.response.AuthTokenResponse;
import com.DanCreate.securityTesting.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private  final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthTokenResponse> register(@RequestBody RegisterDtos registerRequest){
        return ResponseEntity.ok(service.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthTokenResponse> authenticateAccount(@RequestBody AuthenticateDtos authRequest){
        return ResponseEntity.ok(service.authenticateAccount(authRequest));
    }
}
