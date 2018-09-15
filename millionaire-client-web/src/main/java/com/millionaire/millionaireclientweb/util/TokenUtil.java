package com.millionaire.millionaireclientweb.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TokenUtil {
    public static String createToken(Long id,Long loginTime) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("不知名的密钥");
        System.out.println(algorithm);
        String token = JWT.create()
                .withIssuer("孙壮壮")    //签发者签名
                .withSubject("应该是主题内容可惜不再是键值对")
                .withClaim("id",id)
                .withClaim("loginTime",loginTime)
                .withIssuedAt(new Date())
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT getToken(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("不知名的密钥");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);

    }

}
