package com.cya.springblogdemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/13 19:37
 * @description：
 * @modified By：
 * @version:
 */
//令牌
public class JwtUtils {
    //固定签名key
    private static String SECRET_STRING="5J5IjSWcSocbMZLGOmJ0AAo4OwaGKjLE00tUmJEAC4M=";

    private static Key key= Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public static String genToken(Map<String,Object> claims){
        //生成token
        String compact= Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        return compact;
    }

    public static Object parssToken(String token){

        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims=build.parseClaimsJws(token).getBody();
        return claims;
    }
}