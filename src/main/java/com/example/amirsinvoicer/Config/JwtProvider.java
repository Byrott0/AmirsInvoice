package com.example.amirsinvoicer.Config;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtProvider {
    private final String jwtSecret = "geheimeSleutel123";
    private final long jwtExpiration = 86400000; // 1 dag

    // hier wordt de token met een gebruikersnaam gemaakt
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    //haal de gebruikersnaam uit een token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    // controleer of de token geldig is
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Token is ongeldig
            return false;
        }
    }
}