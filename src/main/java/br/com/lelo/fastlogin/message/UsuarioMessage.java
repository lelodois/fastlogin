package br.com.lelo.fastlogin.message;

import javax.validation.constraints.NotNull;

import br.com.lelo.fastlogin.domain.Usuario;

public class UsuarioMessage {

    private Long id;
    private String login;
    private String perfil;

    public UsuarioMessage() {
    }

    public UsuarioMessage(@NotNull Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.perfil = usuario.getPerfil().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}
