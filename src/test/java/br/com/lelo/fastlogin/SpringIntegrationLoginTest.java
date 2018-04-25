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
import br.com.lelo.fastlogin.message.TokenMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationLoginTest {

    @Autowired
    private TestRestTemplate restTemplate;
 
    @Test
    public void loginDeveRetornarSucesso() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo", "lelosenha");

        ResponseEntity<TokenMessage> firstLogin 
                = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);
        this.verifyBySource(firstLogin, "H2");


        ResponseEntity<TokenMessage> secondLogin 
                = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);
        this.verifyBySource(secondLogin, "Redis");

        ResponseEntity<Object> responseLogout = this.logout(secondLogin.getBody().getHash());
        assertEquals(HttpStatus.ACCEPTED, responseLogout.getStatusCode());
    }
    
    @Test
    public void loginDeveRetornarErroSenhaInvalida() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo", "lelosenha1");

        ResponseEntity<TokenMessage> login 
                = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);

        assertEquals(HttpStatus.BAD_REQUEST, login.getStatusCode());
    }
    
    @Test
    public void loginDeveRetornarErroLoginNaoEncontrado() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo1", "lelosenha");

        ResponseEntity<TokenMessage> login 
                = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);

        assertEquals(HttpStatus.NOT_FOUND, login.getStatusCode());
    }
    
    @Test
    public void logoutDeveRetornarErro() throws Exception {
        ResponseEntity<Object> responseLogout = this.logout("qualquercoisa");
        assertEquals(HttpStatus.NOT_FOUND, responseLogout.getStatusCode());
    }

    private ResponseEntity<Object> logout(String token) {
        String url = LoginApi.URI + "sair/" + token;
        return restTemplate.postForEntity(url, null, Object.class);
    }

    private void verifyBySource(ResponseEntity<TokenMessage> response, String source) {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getHash());
        assertEquals("Talvez o redis não esteja habilitado", source, response.getBody().getSource());
    }
}