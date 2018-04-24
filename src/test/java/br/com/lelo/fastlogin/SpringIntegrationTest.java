package br.com.lelo.fastlogin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lelo.fastlogin.controller.LoginApi;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loginDeveRetornarSucesso() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo", "lelosenha");

        ResponseEntity<TokenMessage> response = restTemplate.postForEntity(LoginApi.URI, loginMessage,
                TokenMessage.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getHash());
        assertEquals("H2", response.getBody().getSource());

        response = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getHash());
        assertEquals("Talvez o redis não esteja habilitado", "Redis", response.getBody().getSource());
    }

    @Test
    public void loginDeveRetornarNaoEncontrado() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo-errado", "lelosenha");
        ResponseEntity<String> response = restTemplate.postForEntity(LoginApi.URI, loginMessage, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void loginDeveRetornarSenhaInvalida() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo", "lelosenha-errado");
        ResponseEntity<String> response = restTemplate.postForEntity(LoginApi.URI, loginMessage, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void loginDeveRetornarErroValidacao() throws Exception {

        LoginMessage loginMessage = new LoginMessage(" ", "lelosenha");
        ResponseEntity<String> response = restTemplate.postForEntity(LoginApi.URI, loginMessage, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void statusDeveRetornarUsuarioInfo() throws Exception {
        ResponseEntity<UsuarioMessage> response = restTemplate.getForEntity(LoginApi.URI + "status/lelo",
                UsuarioMessage.class);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getLogin());
        assertNotNull(response.getBody().getPerfil());
        assertNotNull(response.getBody().getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}