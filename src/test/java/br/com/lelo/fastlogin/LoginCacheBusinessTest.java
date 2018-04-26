package br.com.lelo.fastlogin;

import static br.com.lelo.fastlogin.fixture.UsuarioFixture.USUARIO_VALIDO;
import static br.com.six2six.fixturefactory.Fixture.from;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.lelo.fastlogin.business.HashBusiness;
import br.com.lelo.fastlogin.business.LoginCacheBusiness;
import br.com.lelo.fastlogin.cache.RedisRepository;
import br.com.lelo.fastlogin.domain.Usuario;
import br.com.lelo.fastlogin.exception.RedisException;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import net.vidageek.mirror.dsl.Mirror;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginCacheBusinessTest {

    @Mock
    private RedisRepository redisRepository;

    @InjectMocks
    private LoginCacheBusiness loginCacheBusiness;

    @BeforeClass
    public static void beforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.lelo.fastlogin.fixture");
    }

    @Before
    public void before() {
        new Mirror().on(loginCacheBusiness).set().field("hashBusiness").withValue(new HashBusiness());
    }

    @Test
    public void loginDeveNaoRecuperarToken() throws Exception {
        when(redisRepository.get(anyString())).thenReturn(null);
        Optional<String> login = loginCacheBusiness.login("teste", "senha");
        Assert.assertFalse(login.isPresent());
        verify(redisRepository, times(1)).get(anyString());
    }

    @Test
    public void loginDeveRetornarEmptySeORedisEstiverFora() throws RedisException {
        when(redisRepository.get(anyString())).thenThrow(new RedisException());
        Optional<String> login = loginCacheBusiness.login("teste", "senha");
        Assert.assertFalse(login.isPresent());
    }

    @Test
    public void saveDeveAdicionarUsuario() throws Exception {
        Usuario usuario = from(Usuario.class).gimme(USUARIO_VALIDO);
        loginCacheBusiness.save(usuario, "hash");
        verify(redisRepository, times(1)).put(anyString(), anyString());
    }

    @Test
    public void logoutDeveRemoverToken() throws Exception {
        when(redisRepository.get(anyString())).thenReturn(null);
        Usuario usuario = from(Usuario.class).gimme(USUARIO_VALIDO);
        loginCacheBusiness.logout(usuario);
        verify(redisRepository, times(1)).remove(anyString());
    }
}
