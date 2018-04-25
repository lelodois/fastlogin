package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;

@Component
public class LoginBusiness implements Login {

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    @Autowired
    private HashBusiness passwordBusiness;

    @Autowired
    private LoginCacheBusiness loginCacheBusiness;

    @Autowired
    private LoginDataBaseBusiness loginDatabaseBusiness;

    @Override
    public TokenMessage login(LoginMessage loginMessage, String ip) {
        String password = passwordBusiness.getHash(loginMessage.getPassword());
        Optional<String> loginCached = loginCacheBusiness.login(loginMessage.getLogin(), password);

        if (loginCached.isPresent()) {
            return new TokenMessage(loginCached.get(), "Redis");
        }

        return loginDatabaseBusiness.login(loginMessage.getLogin(), password, ip);
    }

    public void logout(String login) {
        Usuario user = usuarioBusiness.findByLoginName(login);
        Optional<String> loginCached = loginCacheBusiness.login(user.getLogin(), user.getPassword());
        if (loginCached.isPresent()) {
            loginCacheBusiness.logout(user);
            loginDatabaseBusiness.logout(loginCached);
        }
    }

}