package com.bes.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author likang
 * @date 2019/8/15 14:52
 */
public class TokenUtil {

    /**
     * 从request里面解密出放入JWT token的自定义集合数据
     * @param headStr 字符串 request中head的key
     * @param tokenStr 字符串 JWT中token的key
     * @return Map<String,String>
     */
    public static Map<String,String> getCommonParamsFromToken(String headStr,String tokenStr){
        Map<String,String> stringMap = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null){
            HttpServletRequest request = requestAttributes.getRequest();
            String head = request.getParameter(headStr);
            JSONObject jsonObject = JSON.parseObject(head);
            String token = jsonObject.get(tokenStr).toString();
            stringMap = JwtUtil.verifyToken(token);
        }
        return stringMap;
    }

    /**
     * 获取每个用户在redis里面缓存的公用数据
     * @param userId 用户编号，redis里面的Key
     * @param stringRedisTemplate RedisTemplate处理字符串的类
     * @return JSONObject
     */
    public static JSONObject getCommonParamsFromRedis(String userId, StringRedisTemplate stringRedisTemplate){
        String data = stringRedisTemplate.opsForValue().get(userId);
        return JSON.parseObject(data);
    }
}
