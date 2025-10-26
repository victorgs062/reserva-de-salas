package com.academico.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    //@IdGeneratorType();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    @Column(name = "nome", length = 250, nullable = false)
    private String nome;

    @Column(name = "email",length = 250, unique = true, nullable = false)
    private String email;

    @Column(name = "senha", length = 250, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    //Construtor?

    //Get e set

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
