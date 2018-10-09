package org.springframework.cache.interceptor;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class CacheExpireOperation {

	private final long ttl;

	public CacheExpireOperation(final long ttl) {
		this.ttl = ttl;
	}

	public long getTtl() {
		return ttl;
	}
}
