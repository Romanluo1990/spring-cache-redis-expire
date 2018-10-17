package roman.springframework.cache.redis;

import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.lang.Nullable;
import roman.springframework.cache.interceptor.CacheExpireOperation;
import roman.springframework.cache.interceptor.CacheExpireOperationManager;

import java.time.Duration;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */
public class ExpirableRedisCacheWriter implements RedisCacheWriter {

	private final RedisCacheWriter delegateRedisCacheWriter;

	ExpirableRedisCacheWriter(final RedisConnectionFactory connectionFactory) {
		this(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory));
	}

	public ExpirableRedisCacheWriter(final RedisCacheWriter delegateRedisCacheWriter) {
		this.delegateRedisCacheWriter = delegateRedisCacheWriter;
	}

	public static RedisCacheWriter nonLockingRedisCacheWriter(final RedisConnectionFactory connectionFactory) {
		return RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
	}

	public static RedisCacheWriter lockingRedisCacheWriter(final RedisConnectionFactory connectionFactory) {
		return RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
	}

	@Override
	public void put(final String name, final byte[] key, final byte[] value, @Nullable final Duration ttl) {
		final Duration duration = getDuration(ttl);
		delegateRedisCacheWriter.put(name, key, value, duration);
	}

	@Override
	@Nullable
	public byte[] get(final String name, final byte[] key) {
		return delegateRedisCacheWriter.get(name, key);
	}

	@Override
	@Nullable
	public byte[] putIfAbsent(final String name, final byte[] key, final byte[] value, @Nullable final Duration ttl) {
		final Duration duration = getDuration(ttl);
		return delegateRedisCacheWriter.putIfAbsent(name, key, value, duration);
	}

	@Override
	public void remove(final String name, final byte[] key) {
		delegateRedisCacheWriter.remove(name, key);
	}

	@Override
	public void clean(final String name, final byte[] pattern) {
		delegateRedisCacheWriter.clean(name, pattern);
	}

	private Duration getDuration(@Nullable final Duration ttl) {
		Duration duration = ttl;
		final CacheExpireOperation cacheExpireOperation = CacheExpireOperationManager.get();
		if (cacheExpireOperation != null)
			duration = Duration.ofMillis(cacheExpireOperation.getTtl());
		return duration;
	}
}
