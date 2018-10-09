package org.springframework.cache.interceptor;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class ExpireCahceInterceptor extends CacheInterceptor {

	private final SpringExpireCacheSource springExpireCacheSource = new SpringExpireCacheSource();

	@Nullable
	@Override
	protected Object execute(final CacheOperationInvoker invoker, final Object target, final Method method,
			final Object[] args) {
		final Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
		final CacheExpireOperation expireOperation = springExpireCacheSource.getExpireOperation(method, targetClass);
		if (targetClass != null) {
			CacheExpireOperationManager.set(expireOperation);
		}
		try {
			return super.execute(invoker, target, method, args);
		} finally {
			CacheExpireOperationManager.remove();
		}
	}

}
