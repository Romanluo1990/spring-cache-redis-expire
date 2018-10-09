package org.springframework.data.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Expire;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @Auther: romanluo
 * @Date: 2018/10/9
 */
@SpringBootApplication
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Expire(10000)
	@Cacheable(value = "user", key = "'ids'")
	public List<Long> listUserIds() {
		logger.info("user ids from db");
		return Arrays.asList(1l, 2l, 3l);
	}
}
