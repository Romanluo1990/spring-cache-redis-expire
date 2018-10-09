package org.springframework.cache.annotation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class SpringExpireCacheAnnotationParser {

	@Nullable
	public Expire parseExpireAnnotations(final Method method) {
		if (method.getAnnotation(Cacheable.class) == null && method.getAnnotation(CachePut.class) == null) {
			return null;
		}
		return AnnotationUtils.findAnnotation(method, Expire.class);
	}

}
