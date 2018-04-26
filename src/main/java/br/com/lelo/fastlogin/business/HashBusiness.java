package br.com.lelo.fastlogin.business;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import br.com.lelo.fastlogin.domain.Usuario;

@Component
public class HashBusiness {

    private static final long SALT = 999l;

    private final HashFunction hash = Hashing.md5();

    public void setPasswordHash(Usuario usuario) {
        if (ObjectUtils.allNotNull(usuario, usuario.getPassword()) == false)
            throw new IllegalArgumentException();

        usuario.setPassword(this.getHash(usuario.getPassword()));
    }

    public String getHash(String password) {
        return hash.newHasher().putLong(SALT).putString(password, Charsets.UTF_8).hash().toString();
    }

}