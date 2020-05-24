package com.panda.shortvideo_splash.base;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author jiangzq
 * @description:
 * @date :2020/5/17 11:06
 */
@Retention(RUNTIME) //性能相对没有CLASS要好，运行时注解
@Target(TYPE) //类 接口，注解
public @interface ViewInject {
    int mainLayoutId() default -1;//默认为-1
}
