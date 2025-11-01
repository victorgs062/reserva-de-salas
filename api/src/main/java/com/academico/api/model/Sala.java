package com.academico.api.model;

import com.academico.api.dto.SalaRequestDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sala;

    @Column(name = "nome", length = 250, nullable = false)
    private String nome;

    @Column(name = "capacidade", nullable = false)
    private int capacidade;

    @Column(name = "recursos", length = 250, nullable = false)
    private String recursos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TipoSala tipoSala;

    @Enumerated(EnumType.STRING)
    @Column(name = "bloco", nullable = false)
    private TipoSalaBloco tipoSalaBloco;

    @OneToMany(mappedBy = "sala")
    private List<Reserva> reservas;

    // Construtor

    public Sala() {}

    public Sala(SalaRequestDTO dto) {
        this.nome = dto.nome();
        this.capacidade = dto.capacidade();
        this.recursos = dto.recursos();
        this.tipoSala = dto.tipoSala();
        this.tipoSalaBloco = dto.tipoSalaBloco();
    }

    // Métodos

    public void atualizar(SalaRequestDTO dto) {
        this.nome = dto.nome();
        this.capacidade = dto.capacidade();
        this.recursos = dto.recursos();
        this.tipoSala = dto.tipoSala();
        this.tipoSalaBloco = dto.tipoSalaBloco();
    }

    // Getters e Setters

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public TipoSalaBloco getTipoSalaBloco() {
        return tipoSalaBloco;
    }

    public void setTipoSalaBloco(TipoSalaBloco tipoSalaBloco) {
        this.tipoSalaBloco = tipoSalaBloco;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
