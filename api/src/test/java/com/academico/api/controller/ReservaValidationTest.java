package com.academico.api.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.ReservaRequestDTO;
import com.academico.api.model.StatusReserva;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void dataVazia(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.ATIVA,
                1,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina a data!"))
        );
    }

    @Test
    void dataAnterior(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.ATIVA,
                1,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Não pode definir uma data anterior a hoje!"))
        );
    }

    @Test
    void dataHoraInicioVazia(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
          LocalDateTime.now(),
          null,
          LocalDateTime.now(),
          StatusReserva.ATIVA,
          1,
          1,
          1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina a data e hora de início!"))
        );
    }

    @Test
    void dataHoraInicioAnterior(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now(),
                StatusReserva.ATIVA,
                1,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Não pode definir uma data anterior a hoje!"))
        );
    }

    @Test
    void dataHoraFimVazia(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                StatusReserva.ATIVA,
                1,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina a data e hora de fim!"))
        );

    }

    @Test
    void dataHoraFimAnterior(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now().minusDays(1),
                StatusReserva.ATIVA,
                1,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Não pode definir uma data anterior a hoje!"))
        );

    }

    @Test
    void statusVazio(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                1,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina o status da reserva!"))
        );

    }

    @Test
    void usuarioVazio(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.ATIVA,
                null,
                1,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina o usuário da sala!"))
        );

    }

    @Test
    void salaVazia(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.ATIVA,
                1,
                null,
                1
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina a sala!"))
        );

    }

    @Test
    void disciplinaVazia(){
        ReservaRequestDTO dto = new ReservaRequestDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                StatusReserva.ATIVA,
                1,
                1,
                null
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina a disciplina da aula!"))
        );
    }

}
