# spring-cache-redis-expire
spring redis框架过期时间注解支持
---

默认spring redis注解缓存不支持过期时间定义，增加Expire注解，支持过期单独key定义过期时间；  
实现逻辑是在ExpireCahceInterceptor.execute时，使用Threadlocal设置过期时间，再在ExpirableRedisCacheWriter.put时获取该时间。


## example

单元测试使用spring boot 2.0测试，spring boot缓存配置参考CacheConfig，测试类ApplicationTests 

测试代码：

```
 @Expire(10000)
 @Cacheable(value = "user", key = "'ids'")
 public List<Long> listUserIds() {
    logger.info("user ids from db");
    return Arrays.asList(1l, 2l, 3l);
 }
```

测试结果：

```
  2018-10-09 15:04:25.963  INFO 36380 --- [           main] Service$$EnhancerBySpringCGLIB$$261954db : user ids from db
  2018-10-09 15:04:25.988  INFO 36380 --- [           main] o.s.data.redis.cache.ApplicationTests    : cache expire ttl: 10
```

