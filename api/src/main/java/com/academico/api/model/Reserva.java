package com.academico.api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reserva;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime data_hora_inicio;

    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime data_hora_fim;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TipoReserva tipoReserva;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private int id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private int id_sala;

    @ManyToOne
    @JoinColumn(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    //Sem construtor, por enquanto

    //Get e set
    public int getId_reserva() {
        return id_reserva;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public LocalDateTime getData_hora_inicio() {
        return data_hora_inicio;
    }

    public void setData_hora_inicio(LocalDateTime data_hora_inicio) {
        this.data_hora_inicio = data_hora_inicio;
    }

    public LocalDateTime getData_hora_fim() {
        return data_hora_fim;
    }

    public void setData_hora_fim(LocalDateTime data_hora_fim) {
        this.data_hora_fim = data_hora_fim;
    }

    public TipoReserva getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(TipoReserva tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }
}
