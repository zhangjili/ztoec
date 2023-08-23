package com.jili.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //作用于类上
@Retention(RetentionPolicy.RUNTIME) //作用于整个代码运行中
@Inherited
public @interface HasInherited {
}
