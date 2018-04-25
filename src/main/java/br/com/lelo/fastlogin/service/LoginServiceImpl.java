package br.com.lelo.fastlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lelo.fastlogin.business.Login;
import br.com.lelo.fastlogin.business.UsuarioBusiness;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;

@Service
public class LoginServiceImpl implements LoginService, Login {

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    @Autowired
    private Login loginBusiness;

    @Override
    public TokenMessage login(LoginMessage loginMessage, String ip) {
        return loginBusiness.login(loginMessage, ip);
    }

    @Override
    public UsuarioMessage getUsuarioStatus(String login) {
        return new UsuarioMessage(usuarioBusiness.findByLoginName(login));
    }

    @Override
    public void logout(String token) {
        loginBusiness.logout(token);
    }

}
