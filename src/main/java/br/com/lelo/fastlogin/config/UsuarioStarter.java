package br.com.lelo.fastlogin.config;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.List;

import javax.annotation.PostConstruct;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.lelo.fastlogin.business.UsuarioBusiness;
import br.com.lelo.fastlogin.domain.Usuario;
import de.svenjacobs.loremipsum.LoremIpsum;

@Configuration
public class UsuarioStarter {

    @Autowired
    private UsuarioBusiness usuarioBusiness;
    private LoremIpsum loremIpsum = new LoremIpsum();

    @PostConstruct
    public void go() {

        Usuario usuario = new Usuario();
        usuario.setLogin("lelo");
        usuario.setPassword("lelosenha");

        List<Usuario> usuarios = Lists.newArrayList(usuario);

        for (int indice = 1; indice <= 10000; indice++) {
            String words = loremIpsum.getWords(20);
            Usuario novo = new Usuario();
            novo.setLogin(words);
            novo.setPassword(join(words, indice, join(words, "senha")));
            usuarios.add(novo);
        }

        usuarioBusiness.save(usuario);
    }

}
