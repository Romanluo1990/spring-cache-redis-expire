package org.springframework.cache.interceptor;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class CacheExpireOperationManager {

	private static final ThreadLocal<CacheExpireOperation> cacheExpireOperationManager = new ThreadLocal<>();

	public static CacheExpireOperation get() {
		return cacheExpireOperationManager.get();
	}

	public static void set(final CacheExpireOperation value) {
		cacheExpireOperationManager.set(value);
	}

	public static void remove() {
		cacheExpireOperationManager.remove();
	}
}
