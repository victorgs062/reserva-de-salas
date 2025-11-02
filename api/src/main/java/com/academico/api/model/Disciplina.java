package com.academico.api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_disciplina;

    @Column(name = "nome", length = 250, nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false, unique = true)
    private int codigo;

    @Column(name = "qtd_alunos", nullable = false)
    private int qtd_alunos;

    @ManyToOne
    @JoinColumn(name = "id_professor", referencedColumnName = "id_usuario", nullable = false)
    private Usuario professor;

    @OneToMany(mappedBy = "disciplina")
    private List<Reserva> reservas;

    // Construtor

    public Disciplina() {}

    public Disciplina(String nome, int codigo, int qtd_alunos, Usuario professor) {
        this.nome = nome;
        this.codigo = codigo;
        this.qtd_alunos = qtd_alunos;
        this.professor = professor;
    }

    // Métodos

    public void atualizarDados(String nome, int codigo, int qtd_alunos, Usuario professor) {
        this.nome = nome;
        this.codigo = codigo;
        this.qtd_alunos = qtd_alunos;
        this.professor = professor;
    }

    // Getters e Setters

    public int getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(int id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQtd_alunos() {
        return qtd_alunos;
    }

    public void setQtd_alunos(int qtd_alunos) {
        this.qtd_alunos = qtd_alunos;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
