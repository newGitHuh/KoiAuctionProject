package com.demo.demo.controller.authenticateController;

import com.demo.demo.jwtService.JwtServiceImpl;
import com.demo.demo.model.Account;
import com.demo.demo.requestDto.LoginRequest;
import com.demo.demo.responseStatus.TokenResponse;
import com.demo.demo.service.accountService.AccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final AccountServiceImp accountServiceImp;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    public TokenResponse authenticate(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        var account = accountServiceImp.findByName(loginRequest.getUsername());

        String token = jwtService.generateToken(account.getUsername());

        TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(token)
                .refreshToken("")
                .userId(String.valueOf(account.getAccountID()))
                .build();

        return tokenResponse;
    }
}
