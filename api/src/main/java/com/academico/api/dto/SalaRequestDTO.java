package com.academico.api.dto;

import com.academico.api.model.TipoSala;
import com.academico.api.model.TipoSalaBloco;
import jakarta.validation.constraints.*;

public record SalaRequestDTO(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(max = 250, message = "Nome maior do que permitido!")
        String nome,
        @NotNull(message = "É obrigatório definir a capacidade da sala!")
        @PositiveOrZero(message = "A capacidade da sala não pode ser negativa!")
        Integer capacidade,
        @NotBlank(message = "É obrigatório descrever os recursos!")
        @Size(max = 250, message = "Descrição maior do que permitida!")
        String recursos,
        @NotNull(message = "Defina se a sala está ativa ou não!")
        TipoSala tipoSala,
        @NotNull(message = "Defina o bloco da sala!")
        TipoSalaBloco tipoSalaBloco) {
}
