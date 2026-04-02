package com.academico.api;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.DisciplinaRequestDTO;
import static org.junit.jupiter.api.Assertions.*;

//TESTE UNITÁRIO
//
//Esta classe testa as validações do DTO DisciplinaRequestDTO.
//
//Tipo de teste:
//Teste Unitário: valida apenas as regras do DTO, sem subir contexto Spring
//
//Objetivo:
//Garantir que as regras de validação (Bean Validation) estejam funcionando corretamente
//Evitar entrada de dados inválidos na aplicação
public class DisciplinaValidationTest {

    // Responsável por executar as validações do DTO
    private Validator validator;

    // Inicializa o validator antes de cada teste
    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    // Testa se o nome vazio é invalidado corretamente
    @Test
    void nomeVazio(){
        DisciplinaRequestDTO dto = new DisciplinaRequestDTO(
          "",
          1121,
          54,
          2
        );

        var invalidacao = validator.validate(dto);

        // Deve existir erro de validação
        assertFalse(invalidacao.isEmpty());

        // Verifica mensagem específica
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O nome é obrigatório!"))
        );
    }

    // Testa se o nome ultrapassando o limite permitido é rejeitado
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

    // Testa se o código da disciplina não pode ser nulo
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

    // Testa se a quantidade de alunos não pode ser nula
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

    // Testa se a quantidade de alunos não pode ser negativa
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

    // Testa se o professor responsável não pode ser nulo
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
