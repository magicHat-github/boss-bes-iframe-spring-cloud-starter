package annotion;

import java.lang.annotation.*;

/**
 *  统一异常验证注解声明
 *
 * @author fishkk
 * @version 1.0.0
 * @since 2019/8/13
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionHandle {
}
