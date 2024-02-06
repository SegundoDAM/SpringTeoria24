package com.adorno.security.jwt;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {
//	/	EncryptedKeyGenerator en web 256 en hex https://asecuritysite.com/encryption/plain
	@Value("${jwt.secret.key}")
	private String secretKey;
	
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	
	public String generateAccessToken(String username) {
		String compact = Jwts
		.builder()
		//la parte publica
		.issuedAt(Date.valueOf(LocalDate.now().plus(1,ChronoUnit.DAYS)))
		.subject(username)
		//la parte secreta
		.signWith(getSignatureKey())
		.compact();
		log.debug("JwtUtils: creandotoken "+compact);
		return compact;
		
	}
//	public Boolean isTokenValid(String token) {}
//	public String getUSerNameFromToken(String token) {}
	private SecretKey getSignatureKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}
	
	
}
