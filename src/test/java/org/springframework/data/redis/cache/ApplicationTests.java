package org.springframework.data.redis.cache;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/8
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserService.class, CacheConfig.class})
public class ApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private UserService userService;

	@Resource
	private RedisTemplate redisTemplate;

	@Test
	public void testCache() {
		userService.listUserIds();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		logger.info("cache expire ttl: {}", redisTemplate.getExpire("user::ids"));
		userService.listUserIds();
	}


}
