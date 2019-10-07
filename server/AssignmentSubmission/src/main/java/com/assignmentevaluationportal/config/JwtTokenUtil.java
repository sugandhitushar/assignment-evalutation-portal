package com.assignmentevaluationportal.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.assignmentevaluationportal.constants.JWTConstants;
import com.assignmentevaluationportal.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
		
    @Value("${jwt.secret}")
    private String secret;

    // Retrieve username from JWT Token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    
    // Retrieve token expiration date
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    // Get a field from token body
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        log.debug("Claims: {}", claims);
        return claimsResolver.apply(claims);
    }

    // Get all fields of token's body
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    // Check if the token is expired
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate a new token for user
    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWTConstants.CLAIM_USER_TYPE_NAME, user.getUserType());
        return doGenerateToken(claims, user.getEmail(), JWTConstants.JWT_ACCESS_TOKEN_VALIDITY_IN_MINUTES);
    }
    
 // Generate a new token for user
    public String generateRefreshToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JWTConstants.CLAIM_USER_TYPE_NAME, user.getUserType());
        
        return Jwts.builder()
        		.setClaims(claims)
        		.setId(UUID.randomUUID().toString())
        		.setSubject(user.getEmail())
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(JWTConstants.JWT_REFRESH_TOKEN_VALIDITY_IN_MINUTES)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    private String doGenerateToken(Map<String, Object> claims, String subject, Long validityInMinutes) {
        return Jwts.builder()
        		.setClaims(claims)
        		.setSubject(subject)
        		.setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(validityInMinutes)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
