package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.NotFoundItemException;
import br.com.lelo.fastlogin.repository.UsuarioRepository;

@Component
public class UsuarioBusiness {

    @Autowired
    public UsuarioRepository repository;

    public String login(Usuario usuario, String ip) {
        Optional<Usuario> optionalUsuario = repository.findOne(Example.of(usuario));
        if (optionalUsuario.isPresent()) {
            return RandomStringUtils.random(20);
        }
        throw new NotFoundItemException();
    }

}