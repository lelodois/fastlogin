package br.com.lelo.fastlogin.fixture;

import static br.com.six2six.fixturefactory.Fixture.of;

import br.com.lelo.fastlogin.domain.Usuario;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UsuarioFixture implements TemplateLoader {

    public static final String USUARIO_PASS_NULLABLE = "usuario-nullable";
    public static final String USUARIO_VALIDO = "usuario-valido";

    @Override
    public void load() {
        of(Usuario.class).addTemplate(USUARIO_VALIDO, new Rule() {
            {
                add("login", "loginvalido");
                add("password", "senhavalida");
            }
        });

        of(Usuario.class).addTemplate(USUARIO_PASS_NULLABLE, new Rule() {
            {
                add("login", "loginvalido");
            }
        });
    }
}
