package com.academico.api;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.ReservaRequestDTO;
import com.academico.api.model.StatusReserva;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

// Teste unitário responsável por validar as regras do DTO ReservaRequestDTO
// Garante que reservas não sejam criadas com dados inválidos
public class ReservaValidationTest {

    // Responsável por executar as validações do DTO
    private Validator validator;

    // Inicializa o validator antes de cada teste
    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    // Testa se a data da reserva é obrigatória
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

        // Deve haver erro
        assertFalse(invalidacao.isEmpty());

        // Verifica mensagem
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina a data!"))
        );
    }

    // Testa se a data não pode ser anterior ao dia atual
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

    // Testa se a data/hora de início é obrigatória
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

    // Testa se a data/hora de início não pode ser anterior ao momento atual
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

    // Testa se a data/hora de fim é obrigatória
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

    // Testa se a data/hora de fim não pode ser anterior ao momento atual
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

    // Testa se o status da reserva é obrigatório
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

    // Testa se o usuário é obrigatório
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

    // Testa se a sala é obrigatória
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

    // Testa se a disciplina é obrigatória
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
