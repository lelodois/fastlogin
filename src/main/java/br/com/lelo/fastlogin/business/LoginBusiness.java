package br.com.lelo.fastlogin.business;

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
    public AcessoRepository acessoRepository;

    @Autowired
    public UsuarioBusiness usuarioBusiness;

    @Autowired
    public PasswordBusiness passwordBusiness;

    public String login(LoginMessage loginMessage, String ip) {
        Usuario model = this.getUserByLoginPassword(loginMessage);
        String hash = RandomStringUtils.random(30);
        acessoRepository.save(new Acesso(hash, model.getId(), ip));
        return hash;
    }

    private Usuario getUserByLoginPassword(LoginMessage loginMessage) {
        Usuario exists = usuarioBusiness.findByLoginName(loginMessage.getLogin());
        String password = passwordBusiness.getHash(loginMessage.getPassword());

        if (exists.getPassword().equals(password)) {
            return exists;
        }
        throw new InvalidPasswordException();
    }

}