package com.academico.api.dto;

import com.academico.api.model.TipoSala;
import com.academico.api.model.TipoSalaBloco;

public record SalaRequestDTO(String nome,
                             int capacidade,
                             String recursos,
                             TipoSala tipoSala,
                             TipoSalaBloco tipoSalaBloco) {
}
