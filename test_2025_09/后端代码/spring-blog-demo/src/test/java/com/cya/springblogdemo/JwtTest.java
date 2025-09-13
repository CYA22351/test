package com.cya.springblogdemo;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.beans.Encoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：陈奕安（3048279304@qq.com）
 * @date ：Created in 2025/9/13 18:15
 * @description：
 * @modified By：
 * @version:
 */
public class JwtTest {
    @Test
    void genToken(){
        Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",12);
        claims.put("name","zhangsan");
        //生成token
        String compact=Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        System.out.println(compact);

        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        System.out.println(build.parse(compact).getBody());
    }
    @Test
public void genKey(){
        Key key=Keys.secretKeyFor(SignatureAlgorithm.HS256);
    String encode = Encoders.BASE64.encode(key.getEncoded());
    System.out.println(encode);
}

}