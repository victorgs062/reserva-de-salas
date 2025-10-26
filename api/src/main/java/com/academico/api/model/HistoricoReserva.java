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
    @JoinColumn(name = "id_reserva", nullable = false)
    private int id_reserva;

    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime data_alteracao;

    @Column(name = "alteracao", length = 250, nullable = false)
    private String alteracao;

    @ManyToOne
    @JoinColumn(name = "usuario_responsavel", referencedColumnName = "id_usuario", nullable = false)
    private int usuario_responsavel;

    //Sem construtor

    //Get e Set
    public int getId_historico() {
        return id_historico;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
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

    public int getUsuario_responsavel() {
        return usuario_responsavel;
    }

    public void setUsuario_responsavel(int usuario_responsavel) {
        this.usuario_responsavel = usuario_responsavel;
    }
}
