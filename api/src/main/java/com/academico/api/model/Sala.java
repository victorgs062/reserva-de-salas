package com.academico.api.model;

import jakarta.persistence.*;

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

    //Construtor?
    
    //Get e set
    public int getId_sala() {
        return id_sala;
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
}
