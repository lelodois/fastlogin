package br.com.lelo.fastlogin.config;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import br.com.lelo.fastlogin.business.UsuarioBusiness;
import br.com.lelo.fastlogin.domain.Perfil;
import br.com.lelo.fastlogin.domain.Usuario;
import de.svenjacobs.loremipsum.LoremIpsum;

@Configuration
public class UsuarioStarter {

    @Autowired
    private UsuarioBusiness usuarioBusiness;
    private LoremIpsum loremIpsum = new LoremIpsum();

    private Map<String, Perfil> perfis = Maps.newHashMap();

    @PostConstruct
    public void go() {

        Usuario usuario = new Usuario();
        usuario.setLogin("lelo");
        usuario.setPassword("lelosenha");
        usuario.setPerfil(this.getUniquePerfil(usuario.getLogin()));

        List<Usuario> usuarios = Lists.newArrayList(usuario);

        for (int indice = 1; indice <= 10000; indice++) {
            String words = loremIpsum.getWords(20);

            Usuario novo = new Usuario();
            novo.setLogin(words);
            novo.setPassword(join(words, indice, join(words, "senha")));
            novo.setPerfil(this.getUniquePerfil(words));

            usuarios.add(novo);
        }

        usuarioBusiness.save(usuario);
    }

    private Perfil getUniquePerfil(String words) {
        String perfilName = words.substring(0, 4);

        if (perfis.containsKey(perfilName) == false)
            perfis.put(perfilName, new Perfil(perfilName));

        return perfis.get(perfilName);
    }

}
