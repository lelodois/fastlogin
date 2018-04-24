package br.com.lelo.fastlogin.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.repository.AcessoRepository;

@Component
public class PasswordBusiness {

    private static final long SALT = 999l;

    @Autowired
    public AcessoRepository repository;

    @Autowired
    public UsuarioBusiness usuarioBusiness;
    private final HashFunction hash = Hashing.sha1();

    public void setPasswordHash(Usuario usuario) {
        usuario.setPassword(this.getHash(usuario.getPassword()));
    }

    public String getHash(String password) {
        return hash.newHasher()
                   .putLong(SALT)
                   .putString(password, Charsets.UTF_8)
                   .hash()
                   .toString();
    }

}