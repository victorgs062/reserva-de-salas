package com.academico.api.dto;

import com.academico.api.model.StatusReserva;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaRequestDTO(
        @NotNull(message = "Defina a data!")
        @FutureOrPresent(message = "Não pode definir uma data anterior a hoje!")
        LocalDateTime data,
        @NotNull(message = "Defina a data e hora de início!")
        @FutureOrPresent(message = "Não pode definir uma data anterior a hoje!")
        LocalDateTime dataHoraInicio,
        @NotNull(message = "Defina a data e hora de fim!")
        @FutureOrPresent(message = "Não pode definir uma data anterior a hoje!")
        LocalDateTime dataHoraFim,
        @NotNull(message = "Defina o status da reserva!")
        StatusReserva status,
        @NotNull(message = "Defina o usuário da sala!")
        Integer id_usuario,
        @NotNull(message = "Defina a sala!")
        Integer id_sala,
        @NotNull(message = "Defina a disciplina da aula!")
        Integer id_disciplina) {
}
