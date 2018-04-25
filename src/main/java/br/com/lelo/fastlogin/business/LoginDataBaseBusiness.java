package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.domain.Acesso;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.InvalidPasswordException;
import br.com.lelo.fastlogin.message.TokenMessage;
import br.com.lelo.fastlogin.repository.AcessoRepository;

@Component
public class LoginDataBaseBusiness {

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private LoginCacheBusiness loginCacheBusiness;

    @Autowired
    private UsuarioBusiness usuarioBusiness;

    public TokenMessage login(String login, String password, String ip) {
        Usuario model = this.getValidatedUser(login, password);
        String hash = RandomStringUtils.random(30);
        this.saveBackgroundAcesso(ip, model, hash);
        return new TokenMessage(hash, "H2");
    }

    public void logout(Optional<String> loginCached) {
        acessoRepository.findById(loginCached.get())
                        .ifPresent(item -> acessoRepository.save(item.logout()));
    }
    
    private Usuario getValidatedUser(String login, String password) {
        Usuario exists = usuarioBusiness.findByLoginName(login);

        if (exists.getPassword().equals(password)) {
            return exists;
        }
        throw new InvalidPasswordException();
    }

    private void saveBackgroundAcesso(String ip, Usuario model, String hash) {
        new Thread(new Runnable() {
            public void run() {
                loginCacheBusiness.save(model, hash);
                acessoRepository.save(new Acesso(hash, model.getLogin(), ip));
            }
        }).start();
    }


}