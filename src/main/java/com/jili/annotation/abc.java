package com.jili.annotation;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author ZhangGJ
 * @Date 2023/02/10 16:27
 */
@Component
@Inherited
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface abc {

    String value();
}
