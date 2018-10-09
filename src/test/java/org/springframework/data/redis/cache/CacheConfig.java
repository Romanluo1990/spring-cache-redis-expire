package org.springframework.data.redis.cache;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.cache.interceptor.ExpireCahceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
@EnableCaching
public class CacheConfig {

	/*cache config begin*/
	@Bean
	public RedisCacheManager cacheManager(final RedisConnectionFactory redisConnectionFactory,
			final ObjectProvider<RedisCacheConfiguration> redisCacheConfiguration) {
		final RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
				.builder(new ExpirableRedisCacheWriter(redisConnectionFactory));
		final RedisCacheConfiguration defaultRedisCacheConfiguration = redisCacheConfiguration.getIfAvailable();
		if (defaultRedisCacheConfiguration != null)
			builder.cacheDefaults(defaultRedisCacheConfiguration);
		return builder.build();
	}

	@Bean
	public CacheInterceptor expireCahceInterceptor(
			final BeanFactoryCacheOperationSourceAdvisor cacheOperationSourceAdvisor,
			final CacheOperationSource cacheOperationSource) {
		final ExpireCahceInterceptor expireCahceInterceptor = new ExpireCahceInterceptor();
		expireCahceInterceptor.setCacheOperationSources(cacheOperationSource);
		cacheOperationSourceAdvisor.setAdvice(expireCahceInterceptor);
		return expireCahceInterceptor;
	}
	/*cache config finish*/

}
