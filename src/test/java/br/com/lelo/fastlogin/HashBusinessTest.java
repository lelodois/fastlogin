package br.com.lelo.fastlogin;

import static br.com.lelo.fastlogin.fixture.UsuarioFixture.USUARIO_PASS_NULLABLE;
import static br.com.lelo.fastlogin.fixture.UsuarioFixture.USUARIO_VALIDO;
import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.lelo.fastlogin.business.HashBusiness;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(JUnit4.class)
public class HashBusinessTest {

    private HashBusiness hashBusiness = new HashBusiness();

    @BeforeClass
    public static void init() {
        FixtureFactoryLoader.loadTemplates("br.com.lelo.fastlogin.fixture");
    }

    @Test
    public void deveGerarHash() {
        assertEquals("1ff44baab25cc3efcf101a5fa7b94afb", hashBusiness.getHash("String"));
    }

    @Test
    public void deveAtribuirHash() {
        Usuario usuario = from(Usuario.class).gimme(USUARIO_VALIDO);
        hashBusiness.setPasswordHash(usuario);
        assertEquals("fa915af383440cfbd590c111e5ae3eae", usuario.getPassword());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveValidarPassword() {
        Usuario usuario = from(Usuario.class).gimme(USUARIO_PASS_NULLABLE);
        hashBusiness.setPasswordHash(usuario);
    }

}
