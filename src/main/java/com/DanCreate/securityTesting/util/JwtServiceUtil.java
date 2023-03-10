package com.DanCreate.securityTesting.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceUtil {

    private static final  String jwtSecretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private  boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaimFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> getClaim,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(getClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60 *24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims getAllClaimFromToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        /*
          * .parserBuilder()//trinh tao phan tich cu phap
            .setSigningKey(getSignInKey())//tao khoa signature bang cach ma hoa base64
            .build()
            .parseClaimsJws(token)//phan tich quuyen cua jwt bang parameter token
            .getBody();//lay thong tin o phan than cua token
        */
    }
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

