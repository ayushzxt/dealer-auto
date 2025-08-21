package com.example.dealervehicle.security;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${app.jwtSecret}")
	private String jwtSecretBase64;

	@Value("${app.jwtExpirationSeconds:3600}")
	private long jwtExpirationSeconds;

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecretBase64);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username) {
		Instant now = Instant.now();
		return Jwts.builder().setSubject(username).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plusSeconds(jwtExpirationSeconds)))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
	}
}




