package br.com.lelo.fastlogin.service;

import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;

public interface LoginService {

    public TokenMessage login(LoginMessage loginMessage, String ip);

    public UsuarioMessage getUsuarioStatus(String login);

    public void logout(String token);

}
