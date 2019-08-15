package aspect;

import annotation.DaoAopAnnotation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bes.common.utils.JwtUtil;
import com.bes.common.utils.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pojo.common.InsertCommon;
import pojo.common.UpdateCommon;
import pojo.enums.MethodType;
import javax.servlet.http.HttpServletRequest;
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

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Pointcut("@annotation(annotation.DaoAopAnnotation)")
	public void daoAop() {
	}

	/**
	 * 修改入参
	 *
	 * @param joinPoint 切面方法
	 * @return 返回值
	 */
	@Around("daoAop() && @annotation(daoAopAnnotation)")
	public Object doAround(ProceedingJoinPoint joinPoint, DaoAopAnnotation daoAopAnnotation) throws Throwable {
//		//存
//		Common common = new Common(136, "新");
//		redisTemplate.opsForValue().set("commonJson", JSON.toJSONString(common));
//
//		//取得
//		Object commonJson = redisTemplate.opsForValue().get("commonJson");
//		Common commonString = JSON.parseObject(commonJson.toString(), Common.class);

		//获取切面参数及其属性域
		Object[] args = joinPoint.getArgs();


		//为不同的dao操作执行不同规则
		if (MethodType.INSERT.equals(daoAopAnnotation.methodType())) {

			JSONObject redisJsonObject = getJsonObject();
			long orgId = (long) redisJsonObject.get("orgId");
			long companyId =(long) redisJsonObject.get("companyId");
			long createdBy =(long) redisJsonObject.get("createdBy");
			Date createdTime =new Date();
			long updatedBy =(long) redisJsonObject.get("updatedBy");
			Date updatedTime = new Date();
			long version =(long) redisJsonObject.get("long");


			InsertCommon commonString = new InsertCommon(orgId,companyId,createdBy,createdTime,updatedBy,updatedTime,version);
//			Field[] csFields = commonString.getClass().getDeclaredFields();

			//传入参数与公有字段进行比对，符合则注入数据
//			for (Field field : fields) {
//				field.setAccessible(true);
//				for (Field csField : csFields) {
//					if (csField.getName().equals(field.getName())) {
//						field.set(args[0], getFieldValueByName(field.getName(), commonString));
//					}
//				}
//			}
			inject2TO1(args[0],commonString);

			return joinPoint.proceed(args);

		} else if (MethodType.UPDATE.equals(daoAopAnnotation.methodType())) {
			//对组织Id等在缓存中已经存在的进行改变时，需要对缓存和数据库同步更新
			JSONObject redisJsonObject =getJsonObject();
			long orgId = (long) redisJsonObject.get("orgId");
			long companyId =(long) redisJsonObject.get("companyId");
			long updatedBy =(long) redisJsonObject.get("updatedBy");
			Date updatedTime = new Date();
			long version =(long) redisJsonObject.get("long");

			UpdateCommon commonString = new UpdateCommon(orgId,companyId,updatedBy,updatedTime,version);
//			Field[] csFields = commonString.getClass().getDeclaredFields();

			//传入参数与公有字段进行比对，符合则注入数据
//			for (Field field : fields) {
//				field.setAccessible(true);
//				for (Field csField : csFields) {
//					if (csField.getName().equals(field.getName())) {
//						field.set(args[0], getFieldValueByName(field.getName(), commonString));
//					}
//				}
//			}
			inject2TO1(args[0],commonString);

			Object proceed = null;
			try {
				proceed = joinPoint.proceed(args);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}



			return  proceed;

		}


		return joinPoint.proceed(args);
	}

	/**
	 * 根据属性名获取属性值
	 *
	 * @param fieldName
	 * @param o
	 * @return
	 */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter);
			return method.invoke(o);
		} catch (Exception e) {
			return null;
		}
	}


	private  JSONObject getJsonObject(){
		Map<String, String> commonParamsFromToken = TokenUtil.getCommonParamsFromToken("head", "token");
		String userId = commonParamsFromToken.get("id");
		JSONObject jsonObject = TokenUtil.getCommonParamsFromRedis(userId,stringRedisTemplate);
//
//		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = requestAttributes.getRequest();
//		String head = request.getParameter("head");
//		JSONObject jsonObject = JSONObject.parseObject(head);
//		String token = (String) jsonObject.get("token");
//		Map<String,String> map=JwtUtil.(token);
//		String id = map.get("id");
		return jsonObject;
	}

	private static void inject2TO1(Object objectFirst,Object objectSecond) throws IllegalAccessException {
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
