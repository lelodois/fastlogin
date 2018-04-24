package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.cache.RedisRepository;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.RedisException;

@Component
public class LoginCacheBusiness {

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private HashBusiness hashBusiness;

    public Optional<String> login(String login, String password) {
        try {
            String chave = this.getCacheKey(login, password);
            return Optional.ofNullable(redisRepository.get(chave));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void save(Usuario model, String hash) {
        try {
            String key = this.getCacheKey(model.getLogin(), model.getPassword());
            redisRepository.put(key, hash);
        } catch (Exception e) {
            LoggerFactory.getLogger(LoginCacheBusiness.class).error(">>Redis disabled<<");
        }
    }

    public void logout(Usuario usuario) {
        try {
            String key = this.getCacheKey(usuario.getLogin(), usuario.getPassword());
            redisRepository.remove(key);
        } catch (RedisException e) {
            LoggerFactory.getLogger(LoginCacheBusiness.class).error(">>Redis disabled<<");
        }
    }

    private String getCacheKey(String login, String password) {
        return hashBusiness.getHash(login + "-" + password);
    }

}