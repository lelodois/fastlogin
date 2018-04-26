package br.com.lelo.fastlogin.fixture;

import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class LoginFixture implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(LoginMessage.class).addTemplate("login-usuario-desconhecido", new Rule() {
            {
                add("login", instant("Nenhum"));
                add("password", instant("Nenhum"));
            }
        });
    }
}
