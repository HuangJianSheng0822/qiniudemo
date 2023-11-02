package com.qn.qiniudemoapi.util;

import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {

    private static long time;
    private static String signature; //签名

    public void setTime(long time) {
        this.time = time;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public static String  encryption(String id){
        JwtBuilder jwtBuilder = Jwts.builder();
        //jwt 由header playload sign 组成
        String jwtToken=jwtBuilder
                //header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload 载荷
                .claim("id",id)
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //signature 签名
                .signWith(SignatureAlgorithm.HS256,signature)
                //将三部分连接起来
                .compact();
        return jwtToken;

    }

    /**
     * 通过jwt获取username
     * @return
     */
    public static String decrypt(String jwt){
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(signature).parseClaimsJws(jwt);
        Claims claims = claimsJws.getBody();
        String id = (String) claims.get("id");
        return id;
    }
}
