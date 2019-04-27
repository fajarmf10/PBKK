package com.fajarmf.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class SecurityServiceImpl implements SecurityService {
//	private static final String secretKey = "Ibkyz06PGlbZWrzUH98WGJEgESviPeLAB9D4B5C665C6EC231D3C3493D6B65";

	private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	@Override
	public String createToken(String subject, long ttlMillis) {
		// TODO Auto-generated method stub
		if(ttlMillis <= 0) {
			throw new RuntimeException("Expiry time must be greater than zero : ["+ttlMillis+"]");
		}
		
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
//		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//		JwtBuilder builder = Jwts.builder().setSubject(subject).signWith(signatureAlgorithm, signingKey);
		long nowMillis = System.currentTimeMillis();
//		builder.setExpiration(new Date(nowMillis + ttlMillis));
		
		String jws = Jwts.builder().setSubject(subject).signWith(secretKey).setExpiration(new Date(nowMillis + ttlMillis)).compact();
		return jws;
	}

	@Override
	public String getSubject(String token) {
		// TODO Auto-generated method stub
//		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
//				.parseClaimsJws(token).getBody();
		String claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		return claims;
	}

}
