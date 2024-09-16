package com.demo.demo.controller.authenticateController;


import com.demo.demo.requestDto.LoginRequest;
import com.demo.demo.responseStatus.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest loginRequest) {

            TokenResponse tokenResponse = authenticateService.authenticate(loginRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
