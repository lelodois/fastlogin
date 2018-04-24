package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.domain.Acesso;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.InvalidPasswordException;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.repository.AcessoRepository;

@Component
public class LoginBusiness {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    @Autowired
    private HashBusiness passwordBusiness;

    @Autowired
    private LoginCacheBusiness loginCacheBusiness;

    public String login(LoginMessage loginMessage, String ip) {
        String password = passwordBusiness.getHash(loginMessage.getPassword());
        Optional<String> loginCached = loginCacheBusiness.login(loginMessage.getLogin(), password);

        if (loginCached.isPresent()) {
            return loginCached.get();
        }

        return this.loginWithDataBaseFallback(loginMessage.getLogin(), password, ip);
    }

    private String loginWithDataBaseFallback(String login, String password, String ip) {
        Usuario model = this.getValidatedUser(login, password);
        String hash = RandomStringUtils.random(30);
        acessoRepository.save(new Acesso(hash, model.getId(), ip));
        loginCacheBusiness.salvarRedis(model, hash);
        return hash;
    }

    private Usuario getValidatedUser(String login, String password) {
        Usuario exists = usuarioBusiness.findByLoginName(login);

        if (exists.getPassword().equals(password)) {
            return exists;
        }
        throw new InvalidPasswordException();
    }
}