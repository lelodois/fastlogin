package br.com.lelo.fastlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lelo.fastlogin.business.UsuarioBusiness;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.message.LoginMessage;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    @Override
    public String login(LoginMessage loginMessage, String ip) {
        return usuarioBusiness.login(new Usuario(loginMessage), ip);
    }

}
