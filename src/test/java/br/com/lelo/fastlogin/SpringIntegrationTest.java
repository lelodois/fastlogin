package br.com.lelo.fastlogin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lelo.fastlogin.controller.LoginApi;
import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

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
        String url = LoginApi.URI + "status/lelo";
        ResponseEntity<UsuarioMessage> response = restTemplate.getForEntity(url, UsuarioMessage.class);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getLogin());
        assertNotNull(response.getBody().getPerfil());
        assertNotNull(response.getBody().getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}