package br.com.lelo.fastlogin.service;

import br.com.lelo.fastlogin.message.LoginMessage;

public interface LoginService {

    public String login(LoginMessage loginMessage, String ip);

}
