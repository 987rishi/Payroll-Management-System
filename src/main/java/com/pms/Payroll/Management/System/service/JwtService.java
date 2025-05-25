package com.pms.Payroll.Management.System.service;


import com.pms.Payroll.Management.System.models.AppUser;
import com.pms.Payroll.Management.System.repo.AppUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;

@Service
public class JwtService {

  @Autowired
  private AppUserRepo appUserRepo;

  @Value("${jwt.secret}")
  private String secret;


  public String generateToken(@NotNull @Email String email) {

    AppUser user = appUserRepo
            .findByUserEmail(email)
            .orElseThrow(() -> new NoSuchElementException(
                    "USER DOES NOT EXIST WITH EMAIL: " + email
            ));

    Map<String, Object> claims = new HashMap<>();
    claims.put("role", user.getRole().getRoleName());

    return Jwts.builder()
            .subject(email)
            .claims()
            .add(claims)
            .and()
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
  }



  public String extractUsername(String jwtToken) {
    return extractClaim(jwtToken , Claims::getSubject);

  }
  public String extractRole(String jwtToken) {
    return extractClaim(jwtToken, claims -> {
      return claims.get("role", String.class);
    });
  }

  private Claims getAllClaims(final String token) {
    return Jwts
            .parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

  }

  private <T> T extractClaim(final String token,
                             Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public boolean validateToken(String jwtToken, UserDetails userDetails) {
    final String userName = extractClaim(jwtToken, Claims::getSubject);
    return (userDetails.getUsername().equals(userName) && !isTokenExpired(jwtToken));

  }

  private boolean isTokenExpired(String jwtToken) {
    return extractClaim(jwtToken, Claims::getExpiration).before(new Date());
  }

  private SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
