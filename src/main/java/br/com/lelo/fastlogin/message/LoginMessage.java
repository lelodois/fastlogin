package br.com.lelo.fastlogin.message;

import javax.validation.constraints.NotBlank;

public class LoginMessage {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public LoginMessage() {

    }

    public LoginMessage(@NotBlank String login, @NotBlank String password) {
        super();
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
