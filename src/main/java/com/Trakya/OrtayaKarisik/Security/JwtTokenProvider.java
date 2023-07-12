package com.Trakya.OrtayaKarisik.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${questapp.app.secret}")
    private String APP_SECRET;

    @Value("${questapp.app.issuer:DODO}")
    private String ISSUER;


    @Value("${questapp.expires.in}")
    private long EXPIRES_IN;

    public String generateToken(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + (EXPIRES_IN * 60 * 1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(APP_SECRET.getBytes()));
    }

    public DecodedJWT verifyJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(APP_SECRET.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier =
                JWT.require(algorithm).acceptExpiresAt(EXPIRES_IN * 60).build();
        try {
            return verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage().toString());
        }
    }

}