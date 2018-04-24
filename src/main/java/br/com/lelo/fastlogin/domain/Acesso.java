package br.com.lelo.fastlogin.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_acesso")
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1026372188568268935L;

    @Id
    @Column(unique = true, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String usuarioId;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataLogin;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataLogout;

    @Column
    private String ip;

    public Acesso() {
    }

    public Acesso(String id, String usuario, String ip) {
        this.ip = ip;
        this.id = id;
        this.usuarioId = usuario;
        this.dataLogin = new Date();
    }

    public Acesso logout() {
        this.dataLogout = new Date();
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDataLogin(Date dataLogin) {
        this.dataLogin = dataLogin;
    }

    public Date getDataLogout() {
        return dataLogout;
    }

    public void setDataLogout(Date dataLogout) {
        this.dataLogout = dataLogout;
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

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

}
