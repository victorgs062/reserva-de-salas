package com.academico.api.controller;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.SalaRequestDTO;
import com.academico.api.model.TipoSalaBloco;
import com.academico.api.model.TipoSala;
import static org.junit.jupiter.api.Assertions.*;

public class SalaValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void nomeVazio(){
        SalaRequestDTO dto = new SalaRequestDTO(
                "",
                60,
                "Ar-condicionado",
                TipoSala.ATIVA,
                TipoSalaBloco.B
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O nome é obrigatório!"))
        );
    }

    @Test
    void nomeMaiorQPermitodo(){
        SalaRequestDTO dto = new SalaRequestDTO(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vitae pulvinar neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce id velit et nulla varius facilisis. Integer viverra, elit quis dapibus posuere, lorem erat commodo massa.",
                 65,
                "Cadeira",
                TipoSala.ATIVA,
                TipoSalaBloco.A
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Nome maior do que permitido!"))
        );
    }

    @Test
    void capacidadeNegativa(){
        SalaRequestDTO dto = new SalaRequestDTO(
          "Sala 224",
          -50,
          "Projetor",
          TipoSala.INATIVA,
          TipoSalaBloco.C
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("A capacidade da sala não pode ser negativa!"))
        );
    }

    @Test
    void capacidadeNula(){
        SalaRequestDTO dto = new SalaRequestDTO(
                "Sala 220",
                null,
                "Mesas",
                TipoSala.ATIVA,
                TipoSalaBloco.D
        );
        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("É obrigatório definir a capacidade da sala!"))
        );

    }

    @Test
    void recursosVazio(){
        SalaRequestDTO dto = new SalaRequestDTO(
          "Sala 110",
          55,
          "",
          TipoSala.INATIVA,
          TipoSalaBloco.E
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("É obrigatório descrever os recursos!"))
        );

    }

    @Test
    void recursosMaiorQPermitido(){
        SalaRequestDTO dto = new SalaRequestDTO(
                "Sala 330",
                63,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vitae pulvinar neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce id velit et nulla varius facilisis. Integer viverra, elit quis dapibus posuere, lorem erat commodo massa.",
                TipoSala.ATIVA,
                TipoSalaBloco.A
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Descrição maior do que permitida!"))
        );
    }

    @Test
    void tipoSalaVazio(){
        SalaRequestDTO dto = new SalaRequestDTO(
          "Sala 120",
          53,
          "Várias coisas",
          null,
          TipoSalaBloco.D
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina se a sala está ativa ou não!"))
        );
    }

    @Test
    void tipoSalaBlocoVazio(){
        SalaRequestDTO dto = new SalaRequestDTO(
          "Sala 312",
          67,
          "Cadeira",
          TipoSala.INATIVA,
          null
        );
        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Defina o bloco da sala!"))
        );
    }

}
