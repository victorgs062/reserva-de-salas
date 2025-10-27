package com.academico.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HistoricoReserva")
public class HistoricoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historico;

    @ManyToOne
    @JoinColumn(name = "id_reserva", referencedColumnName = "id_reserva", nullable = false)
    private Reserva reserva;

    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime data_alteracao;

    @Column(name = "alteracao", length = 250, nullable = false)
    private String alteracao;

    @ManyToOne
    @JoinColumn(name = "usuario_responsavel", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioResponsavel;

    // Construtor

    public HistoricoReserva() {}

    // Getters e Setters

    public int getId_historico() {
        return id_historico;
    }

    public void setId_historico(int id_historico) {
        this.id_historico = id_historico;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LocalDateTime getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(LocalDateTime data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    public String getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(String alteracao) {
        this.alteracao = alteracao;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
}
