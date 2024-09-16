package com.demo.demo.jwtService;

public interface JwtService {
    String generateToken(String username);

    String extractUsername(String token);


}
