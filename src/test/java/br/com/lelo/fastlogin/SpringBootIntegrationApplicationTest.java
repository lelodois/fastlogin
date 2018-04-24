package br.com.lelo.fastlogin;

import static org.junit.Assert.assertEquals;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootIntegrationApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void deveRetornarNaoEncontrado() throws Exception {

        LoginMessage loginMessage = new LoginMessage("lelo", "lelosenha");
        ResponseEntity<String> response = restTemplate.postForEntity(LoginApi.URI, loginMessage, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deveRetornarErroDeValidacao() throws Exception {

        LoginMessage loginMessage = new LoginMessage(" ", "lelosenha");
        ResponseEntity<String> response = restTemplate.postForEntity(LoginApi.URI, loginMessage, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}