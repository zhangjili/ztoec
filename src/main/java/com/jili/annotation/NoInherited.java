package com.jili.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.RUNTIME) //作用于整个代码运行中
public @interface NoInherited {
}
