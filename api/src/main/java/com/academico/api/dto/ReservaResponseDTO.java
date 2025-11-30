package com.academico.api.dto;

import com.academico.api.model.StatusReserva;
import com.academico.api.model.TipoSalaBloco;

import java.time.LocalDateTime;

public record ReservaResponseDTO(int id_reserva,
                                 LocalDateTime data,
                                 LocalDateTime dataHoraInicio,
                                 LocalDateTime dataHoraFim,
                                 StatusReserva status,
                                 String nomeUsuario,
                                 String nomeSala,
                                 String nomeDisciplina,
                                 int id_sala,
                                 TipoSalaBloco bloco) {
}
