package br.com.lelo.fastlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lelo.fastlogin.business.UsuarioBusiness;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    @Override
    public String login(LoginMessage loginMessage, String ip) {
        return usuarioBusiness.login(new Usuario(loginMessage), ip);
    }

    @Override
    public UsuarioMessage getUsuarioStatus(String login) {
        return new UsuarioMessage(usuarioBusiness.findByLoginName(new Usuario(login)));
    }

}
