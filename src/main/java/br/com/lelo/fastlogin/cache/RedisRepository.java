package br.com.lelo.fastlogin.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import br.com.lelo.fastlogin.exception.RedisException;

@Repository
public class RedisRepository {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public void flushAll() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    public void put(String key, String value) throws RedisException {
        try {
            redisTemplate.opsForValue().set(key, value);
            setExpireInBackground(key);
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

    public void remove(String chave) throws RedisException {
        try {
            redisTemplate.delete(chave);
        } catch (Throwable e) {
            throw new RedisException();
        }
    }

    private void setExpireInBackground(String key) {
        new Thread(new Runnable() {
            public void run() {
                redisTemplate.expire(key, 2, TimeUnit.HOURS);
            }
        }).start();
    }
}