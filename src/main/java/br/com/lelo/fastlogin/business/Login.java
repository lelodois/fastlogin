package br.com.lelo.fastlogin.business;

import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;

public interface Login {

    public TokenMessage login(LoginMessage loginMessage, String ip);

    public void logout(String token);

}
