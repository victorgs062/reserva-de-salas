package com.academico.api.dto;

import com.academico.api.model.StatusReserva;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ReservaRequestDTO(LocalDateTime data,
                                LocalDateTime dataHoraInicio,
                                LocalDateTime dataHoraFim,
                                StatusReserva status,
                                int id_usuario,
                                int id_sala,
                                int id_disciplina) {
}
