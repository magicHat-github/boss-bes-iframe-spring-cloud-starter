package com.boss.bes.common.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
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
     * @return Map<String,String> OR null
     */
    public static Map<String,String> getCommonParamsFromToken(String headStr,String tokenStr) throws IOException {
        Map<String,String> stringMap = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null){
            HttpServletRequest request = requestAttributes.getRequest();
            String body = getRequestBody(request);
            if(StrUtil.isNotEmpty(body)) {
                JSONObject jsonObject = JSON.parseObject(body);
                String head = jsonObject.get(headStr).toString();
                if (StrUtil.isNotEmpty(head)) {
                    jsonObject = JSON.parseObject(head);
                    String token = jsonObject.get(tokenStr).toString();
                    if (StrUtil.isNotEmpty(token)) {
                        stringMap = JwtUtil.verifyToken(token);
                    }
                }
            }
        }
        return stringMap;
    }

    /**
     * 获取每个用户在redis里面缓存的公用数据
     * @param userId 用户编号，redis里面的Key
     * @param stringRedisTemplate RedisTemplate处理字符串的类
     * @return JSONObject OR null
     */
    public static JSONObject getCommonParamsFromRedis(String userId, StringRedisTemplate stringRedisTemplate){
        String data = stringRedisTemplate.opsForValue().get(userId);
        return StrUtil.isNotEmpty(data)?JSON.parseObject(data):null;
    }

    /**
     * 获取request的body
     * @param request  request
     * @return String
     * @throws IOException IOException
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str = "";
        StringBuilder listString = new StringBuilder();
        while ((str = br.readLine()) != null) {
            listString.append(str);
        }
        return listString.toString();
    }
}
