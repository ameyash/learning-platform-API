package com.gamified.learning_platform.Common;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Autowired
	private SecretKeyGenerator _keyGenerator;

	private String secret = "vLIAQ48fLJ/ubFR56kuhBwJ8TqbzhI+Yrdn7P5Wuhf4zCRTpstlGNh/siANtWY83UQjTxxZykzPiI3V78EOKZQ==";

	// Lazily initialize the secret when first needed
//    private String getSecret() {
//        if (secret == null) {
//            secret = _keyGenerator.keyGenerator();  // Generate the key only when needed
//        }
//        return secret;
//    }

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes())) // Updated
				.compact();
	}

	public String extractUsername(String token) {
		return Jwts.parser().setSigningKey(secret) // Use getSecret() method
				.parseClaimsJws(token).getBody().getSubject();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return Jwts.parser().setSigningKey(secret) // Use getSecret() method
				.parseClaimsJws(token).getBody().getExpiration().before(new Date());
	}
}
