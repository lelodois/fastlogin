package br.com.lelo.fastlogin.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import br.com.lelo.fastlogin.exception.RedisException;

@Repository
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void put(String key, String value) throws RedisException {
        try {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, 2, TimeUnit.HOURS);
        } catch (Throwable e) {
            throw new RedisException();
        }
    }

    public String get(String key) throws RedisException {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Throwable e) {
            throw new RedisException();
        }
    }

}