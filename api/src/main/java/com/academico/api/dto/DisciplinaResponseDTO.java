package com.academico.api.dto;

public record DisciplinaResponseDTO(int id_disciplina,
                                    String nome,
                                    int codigo,
                                    int qtd_alunos,
                                    int id_professor,
                                    String nomeProfessor) {
}
