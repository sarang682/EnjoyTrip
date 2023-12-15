package com.ssafy.enjoytrip.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {

    public static String getUserName(String token,String key) {
        return extrectClaims(token, key).get("username", String.class);
    }

    public static boolean isExpired(String token,String key) {
        Date expiredDate = extrectClaims(token, key).getExpiration();
        return expiredDate.before(new Date());
    }

    private static Claims extrectClaims(String token, String secret) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    public static String generateToken(String userName, String secret, long expiredTimeMs){
        Claims claims = Jwts.claims();
        claims.put("username",userName);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }


//
//    /**
//     * 토큰 생성
//     */
//    public String generateToken(UserDetails userDetails) {
//        Claims claims = Jwts.claims();
//        claims.put("username", userDetails.getUsername());
//        return createToken(claims, userDetails.getUsername()); // username을 subject로 해서 token 생성
//    }
//
//    private String createToken(Claims claims, String subject) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() +  1000 * 60 * 60 * 10)) // 1시간
//                .signWith(SignatureAlgorithm.HS256, secrete)
//                .compact();
//    }
//
//    /**
//     * 토큰 유효여부 확인
//     */
//    public Boolean isValidToken(String token, UserDetails userDetails) {
//        log.info("isValidToken token = {}", token);
//        String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    /**
//     * 토큰의 Claim 디코딩
//     */
//    public Claims getAllClaims(String token) {
//        log.info("getAllClaims token = {}", token);
//        return Jwts.parser()
//                .setSigningKey(secrete)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    /**
//     * Claim 에서 username 가져오기
//     */
//    public String getUsernameFromToken(String token) {
//        String username = String.valueOf(getAllClaims(token).get("username"));
//        log.info("getUsernameFormToken subject = {}", username);
//        return username;
//    }
//
//    /**
//     * 토큰 만료기한 가져오기
//     */
//    public Date getExpirationDate(String token) {
//        Claims claims = getAllClaims(token);
//        return claims.getExpiration();
//    }
//
//    /**
//     * 토큰이 만료되었는지
//     */
//    private boolean isTokenExpired(String token) {
//        return getExpirationDate(token).before(new Date());
//    }

}
