package br.com.lelo.fastlogin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.message.TokenMessage;
import br.com.lelo.fastlogin.message.UsuarioMessage;
import br.com.lelo.fastlogin.service.LoginService;

@RestController
@RequestMapping(LoginApi.URI)
public class LoginApi {

    public static final String URI = "/login/";

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<TokenMessage> login(
            @Validated @RequestBody(required = true) LoginMessage loginMessage,
            HttpServletRequest request) {
        
        return ResponseEntity.ok(service.login(loginMessage, request.getRemoteHost()));
    }

    @GetMapping("status/{login}")
    public ResponseEntity<UsuarioMessage> status(
            @RequestBody(required = true) @PathVariable("login") String login) {
        
        UsuarioMessage usuario = service.getUsuarioStatus(login);
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("sair/{login}")
    public ResponseEntity<Object> logout(
            @RequestBody(required = true) @PathVariable("login") String login) {
        
        service.logout(login);
        return ResponseEntity.accepted().build();
    }
}
