package com.auth.security;

import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	
	private static final String SECRET_KEY = "aaaaaaaaaaqqqqqqqqqqzzzzzzzzzz1111111111222222222233333333330000";

	public String extractUsername(String jwt) {
		
		return extractClaim(jwt, Claims::getSubject);
		
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {	
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	/*
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		
		return Jwts
				.builder()
				.claims(extraClaims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignKey())
				.compact();
				
		
	}
	*/
	
	public String generateToken(UserEntity userEntity) {
        String token = Jwts
                .builder()
                .subject(userEntity.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(getSignKey())
                .compact();

        return token;
    }
	
	private Claims extractAllClaims(String token) {
		
		return Jwts
				.parser()
                .verifyWith(getSignKey())	
                .build()
                .parseSignedClaims(token)
                .getPayload();
	}
	
	private SecretKey getSignKey() {
		
		byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(KeyBytes);
	}
	
}

