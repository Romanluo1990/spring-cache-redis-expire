package roman.springframework.cache.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class ExpireRedisCacheManager implements CacheManager {

	private final RedisCacheManager delegateRedisCacheManager;

	public ExpireRedisCacheManager(final Map<String, RedisCacheConfiguration> cacheConfigurations,
			final RedisConnectionFactory connectionFactory) {
		final RedisCacheWriter redisCacheWriter = new ExpirableRedisCacheWriter(connectionFactory);
		final RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
		delegateRedisCacheManager = new RedisCacheManager(redisCacheWriter, redisCacheConfiguration,
				cacheConfigurations);
	}

	@Override
	@Nullable
	public Cache getCache(final String name) {
		return delegateRedisCacheManager.getCache(name);
	}

	@Override
	public Collection<String> getCacheNames() {
		return delegateRedisCacheManager.getCacheNames();
	}
}
