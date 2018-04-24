package br.com.lelo.fastlogin.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 6116726674865950790L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "dt_cadastro", updatable = false, nullable = false)
    private Date dataCadastro;

    public final Date getDataCadastro() {
        return dataCadastro;
    }

    public final void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

}
