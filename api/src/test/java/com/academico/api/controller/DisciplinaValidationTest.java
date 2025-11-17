package com.academico.api.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.DisciplinaRequestDTO;
import static org.junit.jupiter.api.Assertions.*;

public class DisciplinaValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void nomeVazio(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
          "",
          1121,
          54,
          2
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O nome é obrigatório!"))
        );
    }

    @Test
    void nomeMaiorQPermitido(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
          "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vitae pulvinar neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce id velit et nulla varius facilisis. Integer viverra, elit quis dapibus posuere, lorem erat commodo massa.",
          1121,
          54,
          2
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Nome maior do que permitido!"))
        );
    }

    @Test
    void codigoNulo(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
                "Matematica",
                null,
                54,
                2
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O código é obrigatório!"))
        );
    }

    @Test
    void alunoNulo(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
                "Matematica",
                1121,
                null,
                2
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Digite a quantidade de alunos!"))
        );
    }

    @Test
    void alunoNegativo(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
                "Matematca",
                1121,
                -54,
                2
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O valor não pode ser negativo!"))
        );
    }

    @Test
    void professorVazio(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
                "Matematica",
                1121,
                54,
                null
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Coloque o professor responsável pela disciplina!"))
        );

    }

}
