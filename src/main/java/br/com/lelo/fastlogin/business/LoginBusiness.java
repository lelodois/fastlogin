package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.domain.Acesso;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.InvalidPasswordException;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;
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

    public TokenMessage login(LoginMessage loginMessage, String ip) {
        String password = passwordBusiness.getHash(loginMessage.getPassword());
        Optional<String> loginCached = loginCacheBusiness.login(loginMessage.getLogin(), password);

        if (loginCached.isPresent()) {
            return new TokenMessage(loginCached.get(), "Redis");
        }

        return this.loginWithDataBaseFallback(loginMessage.getLogin(), password, ip);
    }

    private TokenMessage loginWithDataBaseFallback(String login, String password, String ip) {
        Usuario model = this.getValidatedUser(login, password);
        String hash = RandomStringUtils.random(30);
        acessoRepository.save(new Acesso(hash, model.getId(), ip));
        loginCacheBusiness.save(model, hash);
        return new TokenMessage(hash, "H2");
    }

    private Usuario getValidatedUser(String login, String password) {
        Usuario exists = usuarioBusiness.findByLoginName(login);

        if (exists.getPassword().equals(password)) {
            return exists;
        }
        throw new InvalidPasswordException();
    }
}