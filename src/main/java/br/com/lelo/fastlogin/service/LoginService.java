package br.com.lelo.fastlogin.service;

import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;

public interface LoginService {

    public String login(LoginMessage loginMessage, String ip);

    public UsuarioMessage getUsuarioStatus(String login);

}
