package com.academico.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Usuario")
public class Usuario {

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

    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacoes;

    @OneToMany(mappedBy = "usuarioResponsavel")
    private List<HistoricoReserva> historicosResponsavel;

    // Construtor

    public Usuario() {}

    public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public List<HistoricoReserva> getHistoricosResponsavel() {
        return historicosResponsavel;
    }

    public void setHistoricosResponsavel(List<HistoricoReserva> historicosResponsavel) {
        this.historicosResponsavel = historicosResponsavel;
    }
}
