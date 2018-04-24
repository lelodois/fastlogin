package br.com.lelo.fastlogin.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.lelo.fastlogin.cache.RedisRepository;
import br.com.lelo.fastlogin.exception.RedisException;

@Configuration
public class RedisStarter {

    @Autowired
    private RedisRepository redisRepository;

    @PostConstruct
    public void go() throws RedisException {
        redisRepository.put("start", String.valueOf(new Date().getTime()));
    }

}
