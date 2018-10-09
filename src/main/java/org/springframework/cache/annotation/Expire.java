package org.springframework.cache.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Expire {

	@AliasFor("value") long ttl() default 60000;

	@AliasFor("ttl") long value() default 60000;
}
