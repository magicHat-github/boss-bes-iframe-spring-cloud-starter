package com.boss.bes.core.dao.aop.aspect;

import com.alibaba.fastjson.JSONObject;
import com.boss.bes.common.utils.TokenUtil;
import com.boss.bes.core.dao.aop.annotation.DaoAopAnnotation;
import com.boss.bes.core.dao.aop.pojo.common.InsertCommon;
import com.boss.bes.core.dao.aop.pojo.common.UpdateCommon;
import com.boss.bes.core.dao.aop.pojo.enums.MethodType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * @author Lynch
 * @date 2019/8/11 -23:21
 */
@Aspect
@Component
public class DaoAopAspect {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.boss.bes.core.dao.aop.annotation.DaoAopAnnotation)")
	public void daoAop() {
	}

	/**
	 * 自动填值
	 * @param joinPoint 切面方法
	 * @return 返回值
	 */
	@Around("daoAop() && @annotation(daoAopAnnotation)")
	public Object doAround(ProceedingJoinPoint joinPoint, DaoAopAnnotation daoAopAnnotation) throws Throwable {
		// 获取切面参数及其属性域
		Object[] args = joinPoint.getArgs();
		JSONObject redisJsonObject = getJsonObject();
		Date date = new Date();
		Long orgId = Long.parseLong(redisJsonObject.get("orgId").toString());
		Long companyId = Long.parseLong(redisJsonObject.get("companyId").toString());
		Long updatedBy = Long.parseLong(redisJsonObject.get("updatedBy").toString());

		// 为不同的dao操作执行不同规则
		if (MethodType.INSERT.equals(daoAopAnnotation.methodType())) {
			Long createdBy = Long.parseLong(redisJsonObject.get("createdBy").toString());
			Long version = 0L;

			InsertCommon commonString = new InsertCommon(orgId,companyId,createdBy,date,updatedBy,date,version);
			inject2To1(args[0],commonString);

		} else if (MethodType.UPDATE.equals(daoAopAnnotation.methodType())) {
			// 对组织Id等在缓存中已经存在的进行改变时，需要对缓存和数据库同步更新
			UpdateCommon commonString = new UpdateCommon(orgId,companyId,updatedBy,date);
			inject2To1(args[0],commonString);
		}
		return joinPoint.proceed(args);
	}

	/**
	 * 根据属性名获取属性值
	 * @param fieldName 属性名
	 * @param object 对象
	 * @return 属性值
	 */
	private static Object getFieldValueByName(String fieldName, Object object) {
		Object result = null;
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = object.getClass().getMethod(getter);
			result = method.invoke(object);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取JSONObject从request
	 * @return JSONObject
	 */
	private JSONObject getJsonObject() throws IOException {
		Map<String, String> commonParamsFromToken = TokenUtil.getCommonParamsFromToken("head", "token");
		String userId = commonParamsFromToken.get("id");
		return TokenUtil.getCommonParamsFromRedis(userId,stringRedisTemplate);
	}

	private static void inject2To1(Object objectFirst,Object objectSecond) throws IllegalAccessException {
		Field[] firstFields = objectFirst.getClass().getDeclaredFields();
		Field[] secondFields = objectSecond.getClass().getDeclaredFields();
		for (Field 	firstField : firstFields) {
			firstField.setAccessible(true);
			for (Field secondField : secondFields) {
				if (secondField.getName().equals(firstField.getName())) {
					firstField.set(objectFirst, getFieldValueByName(firstField.getName(), objectSecond));
				}
			}
		}
	}

}
