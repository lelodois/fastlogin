package br.com.lelo.fastlogin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lelo.fastlogin.message.LoginMessage;
import br.com.lelo.fastlogin.service.LoginService;

@RestController
@RequestMapping(LoginApi.URI)
public class LoginApi {

    public static final String URI = "/login/";

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<String> login(
            @Validated @RequestBody(required = true) LoginMessage loginMessage,
            HttpServletRequest request) {
        return ResponseEntity.ok(service.login(loginMessage, request.getRemoteHost()));
    }
}
