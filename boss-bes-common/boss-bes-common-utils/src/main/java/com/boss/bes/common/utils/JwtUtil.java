package com.boss.bes.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author likang
 * @date 2019/8/12 17:26
 */
public class JwtUtil {

    /**
     * 加密算法中加的盐
     */
    private static final String SECRET = "9a96349e9a96349e9a96349e";

    /**
     * 生成token
     * @param claims 传入参数
     * @return String
     */
    public static String genToken(Map<String, String> claims){
        String token;
        try {
            //使用HMAC256进行加密
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //创建jwt
            JWTCreator.Builder builder = JWT.create();
            //传入参数
            claims.forEach(builder::withClaim);
            //签名加密
            token = builder.sign(algorithm);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    /**
     * 解密jwt
     * @param token token
     * @return Map
     * @throws RuntimeException 运行时异常
     */
    public static Map<String,String> verifyToken(String token) throws RuntimeException{
        Algorithm algorithm;
        Map<String, String> resultMap = new HashMap<>(11);
        try {
            //使用HMAC256进行加密
            algorithm = Algorithm.HMAC256(SECRET);
            //解密
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt =  verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            map.forEach((k,v) -> resultMap.put(k, v.asString()));
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }
}
