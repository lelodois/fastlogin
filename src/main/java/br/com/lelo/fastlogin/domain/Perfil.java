package br.com.lelo.fastlogin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_perfil")
public class Perfil extends BaseModel implements Serializable {

    private static final long serialVersionUID = 634347832564162622L;

    @NotNull
    @Column(nullable = false, unique = true, updatable = false)
    private String nome;

    public Perfil() {
    }

    public Perfil(@NotNull String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
