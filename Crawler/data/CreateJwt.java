34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_common/src/test/java/com/tensquare/jwt/CreateJwt.java
package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author 李聪
 * @date 2020/2/18 16:08
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("小马")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast")
                .setExpiration(new Date(new Date().getTime() + 60000))
                .claim("role","admin");
        System.out.println(jwtBuilder.compact());
    }
}
