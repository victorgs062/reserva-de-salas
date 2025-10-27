package com.academico.api.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "Reserva")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_notificacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "mensagem", length = 250, nullable = false)
    private String mensagem;

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime data_envio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoNotificacao tipoNotificacao;

    // Construtor

    public Notificacao() {}

    // Getters e Setters

    public int getId_notificacao() {
        return id_notificacao;
    }

    public void setId_notificacao(int id_notificacao) {
        this.id_notificacao = id_notificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getData_envio() {
        return data_envio;
    }

    public void setData_envio(LocalDateTime data_envio) {
        this.data_envio = data_envio;
    }

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }
}
