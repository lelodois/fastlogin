package br.com.lelo.fastlogin.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_acesso")
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1026372188568268935L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataLogin;

    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private String ip;

    public Acesso() {
    }

    public Acesso(String id, Long usuario, String ip) {
        this.ip = ip;
        this.id = id;
        this.usuarioId = usuario;
        this.dataLogin = new Date();
    }

    public void setDataLogin(Date dataLogin) {
        this.dataLogin = dataLogin;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDataLogin() {
        return dataLogin;
    }

    public String getIp() {
        return ip;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

}
