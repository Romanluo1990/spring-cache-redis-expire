package roman.springframework.cache.interceptor;

import org.springframework.core.MethodClassKey;
import roman.springframework.cache.annotation.Expire;
import roman.springframework.cache.annotation.SpringExpireCacheAnnotationParser;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class SpringExpireCacheSource {

	private final Map<Object, CacheExpireOperation> attributeCache = new ConcurrentHashMap<>();

	private final SpringExpireCacheAnnotationParser springExpireCacheAnnotationParser = new SpringExpireCacheAnnotationParser();

	public CacheExpireOperation getExpireOperation(final Method method, final Class<?> targetClass) {
		if (method.getDeclaringClass() == Object.class) {
			return null;
		}

		final Object cacheKey = new MethodClassKey(method, targetClass);
		final CacheExpireOperation cached = attributeCache.get(cacheKey);

		if (cached != null) {
			return cached;
		} else {
			final Expire expire = springExpireCacheAnnotationParser.parseExpireAnnotations(method);
			final CacheExpireOperation cacheOps = new CacheExpireOperation(expire.ttl());
			if (cacheOps != null) {
				attributeCache.put(cacheKey, cacheOps);
			} else {
				attributeCache.put(cacheKey, null);
			}
			return cacheOps;
		}
	}

}
