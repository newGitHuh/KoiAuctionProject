package com.demo.demo.jwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

        private final String SECRET_KEY = "921B97A9E1CD33BBD5FF5AF781C8C9C68A71B071B970B23962BD331F5D0B5720"; // Use environment variable in production

        public String generateToken(String username){
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        }
        
        private Key getKey(){
            return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        }

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token) {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        }
    }

