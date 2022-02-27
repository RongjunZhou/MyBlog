package com.example.myblog.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "@Zhou#ChengyiDashazi!";

    /**
     *生成token
     */

    public static String getToken(Map<String,Object> map){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,6);

        JWTCreator.Builder builder=JWT.create();

        map.forEach((k, v) -> {
            builder.withClaim(k, v.toString());
        });

        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(SING));


    }

    /**
     *校检token
     */

    public static DecodedJWT verifyToken(String token) {
        //如果有任何验证异常，此处都会抛出异常
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}

