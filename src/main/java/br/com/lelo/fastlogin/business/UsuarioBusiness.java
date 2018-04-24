package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.NotFoundItemException;
import br.com.lelo.fastlogin.repository.UsuarioRepository;

@Component
public class UsuarioBusiness {

    @Autowired
    public UsuarioRepository repository;
    @Autowired
    public PasswordBusiness passwordBusiness;

    public Usuario findByLoginName(String login) {
        Optional<Usuario> usuario = repository.findOne(Example.of(new Usuario(login)));
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new NotFoundItemException();
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void save(Usuario... usuarios) {
        for (Usuario usuario : usuarios) {
            passwordBusiness.setPasswordHash(usuario);
            repository.save(usuario);
        }
    }

}