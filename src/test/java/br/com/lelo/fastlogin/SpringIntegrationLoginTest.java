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

        String user = "lelo";
        this.logout(user);

        LoginMessage loginMessage = new LoginMessage(user, "lelosenha");

        ResponseEntity<TokenMessage> firstLogin 
                = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);
        this.verifyBySource(firstLogin, "H2");


        ResponseEntity<TokenMessage> secondLogin 
                = restTemplate.postForEntity(LoginApi.URI, loginMessage, TokenMessage.class);
        this.verifyBySource(secondLogin, "Redis");

        this.logout(user);
    }

    private void logout(String user) {
        String url = LoginApi.URI + "sair/" + user;
        ResponseEntity<Object> responseLogout = restTemplate.postForEntity(url, null, Object.class);
        assertEquals(HttpStatus.ACCEPTED, responseLogout.getStatusCode());
    }

    private void verifyBySource(ResponseEntity<TokenMessage> response, String source) {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getHash());
        assertEquals("Talvez o redis não esteja habilitado", source, response.getBody().getSource());
    }
}