package com.academico.api.dto;

public record DisciplinaRequestDTO(String nome,
                                   int codigo,
                                   int qtd_alunos,
                                   int id_professor) {
}
