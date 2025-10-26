package com.academico.api.model;

import jakarta.persistence.*;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_disciplina;

    @Column(name = "nome", length = 250, nullable = false)
    private String nome;

    @Column(name = "qtd_alunos", nullable = false)
    private int qtd_alunos;

    @ManyToOne
    @JoinColumn(name = "id_professor", referencedColumnName = "id_usuario", nullable = false)
    private int id_professor;

    //Construtor?

    //Get e Set
    public int getId_disciplina() {
        return id_disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd_alunos() {
        return qtd_alunos;
    }

    public void setQtd_alunos(int qtd_alunos) {
        this.qtd_alunos = qtd_alunos;
    }

    public int getId_professor() {
        return id_professor;
    }

    public void setId_professor(int id_professor) {
        this.id_professor = id_professor;
    }
}
