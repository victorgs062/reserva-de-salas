package com.academico.api.dto;
import jakarta.validation.constraints.*;

public record DisciplinaRequestDTO(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(max = 250, message = "Nome maior do que permitido!")
        String nome,
        @NotNull(message = "O código é obrigatório!")
        Integer codigo,
        @NotNull(message = "Digite a quantidade de alunos!")
        @PositiveOrZero(message = "O valor não pode ser negativo!")
        Integer qtd_alunos,
        @NotNull(message = "Coloque o professor responsável pela disciplina!")
        Integer id_professor) {
}
