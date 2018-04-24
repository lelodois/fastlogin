package br.com.lelo.fastlogin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.lelo.fastlogin.message.LoginMessage;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends BaseModel implements Serializable {

    private static final long serialVersionUID = -7390966735433559979L;

    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private String login;

    @NotNull
    @Column(nullable = false)
    private String password;

    public Usuario() {
    }

    public Usuario(LoginMessage loginMessage) {
        this.login = loginMessage.getLogin();
        this.password = loginMessage.getPassword();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
