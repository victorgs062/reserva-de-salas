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
    @JoinColumn(name = "id_usuario", nullable = false)
    private int id_usuario;

    @Column(name = "mensagem", length = 250, nullable = false)
    private String mensagem;

    @Column(name = "data", nullable = false)
    private LocalDateTime data_envio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoNotificacao tipoNotificacao;

    //Sem construtor aí

    //Get e Set
    public int getId_notificacao() {
        return id_notificacao;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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
