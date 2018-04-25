package br.com.lelo.fastlogin.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lelo.fastlogin.common.PresentObject;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.repository.UsuarioRepository;

@Component
public class UsuarioBusiness {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private HashBusiness passwordBusiness;

    public Usuario findByLoginName(String login) {
        Optional<Usuario> optional = repository.findOne(Example.of(new Usuario(login)));
        return new PresentObject<Usuario>().getOrThrow(optional);
    }

    public Usuario findById(String id) {
        return new PresentObject<Usuario>().getOrThrow(repository.findById(id));
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void save(Usuario... usuarios) {
        for (Usuario usuario : usuarios) {
            passwordBusiness.setPasswordHash(usuario);
            repository.save(usuario);
        }
    }

}