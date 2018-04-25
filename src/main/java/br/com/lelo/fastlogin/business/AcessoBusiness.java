package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lelo.fastlogin.common.PresentObject;
import br.com.lelo.fastlogin.domain.Acesso;
import br.com.lelo.fastlogin.repository.AcessoRepository;

@Component
public class AcessoBusiness {

    @Autowired
    private AcessoRepository acessoRepository;

    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void logout(Optional<String> loginCached) {
        acessoRepository.findById(loginCached.get())
                        .ifPresent(item -> acessoRepository.save(item.logout()));

    }

    @Transactional(rollbackFor = Exception.class, readOnly = false)
    public void save(String hash, String login, String ip) {
        acessoRepository.save(new Acesso(hash, login, ip));
    }

    public Acesso findByToken(String token) {
        return new PresentObject<Acesso>().getOrThrow(acessoRepository.findById(token));
    }

}