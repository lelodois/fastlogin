package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.cache.RedisRepository;
import br.com.lelo.fastlogin.domain.Usuario;

@Component
public class LoginCacheBusiness {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private HashBusiness passwordBusiness;

    public Optional<String> login(String login, String password) {
        try {
            String key = this.getCacheKey(login, password);
            return Optional.of(redisRepository.get(key));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void salvarRedis(Usuario model, String hash) {
        try {
            String key = this.getCacheKey(model.getLogin(), model.getLogin());
            redisRepository.put(key, hash);
        } catch (Exception e) {
            LoggerFactory.getLogger(LoginCacheBusiness.class).error(">>Redis disabled<<");
        }
    }

    private String getCacheKey(String login, String password) {
        return passwordBusiness.getHash(login) + "-" + password;
    }

}