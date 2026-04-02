package com.academico.api;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.SalaRequestDTO;
import com.academico.api.model.TipoSalaBloco;
import com.academico.api.model.TipoSala;
import static org.junit.jupiter.api.Assertions.*;

// Teste unitário responsável por validar as regras do DTO SalaRequestDTO
// Garante que os dados de entrada estejam corretos antes de chegar na lógica de negócio
public class SalaValidationTest {

    // Objeto responsável por executar as validações do DTO
    private Validator validator;

    // Metodo executado antes de cada teste para inicializar o validator
    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    // Testa se o nome da sala não pode ser vazio
    @Test
    void nomeVazio(){
        // Cria um DTO com nome vazio
        SalaRequestDTO dto = new SalaRequestDTO(
                "",
                60,
                "Ar-condicionado",
                TipoSala.ATIVA,
                TipoSalaBloco.B
        );

        var invalidacao = validator.validate(dto);

        // Verifica se houve erro
        assertFalse(invalidacao.isEmpty());

        // Verifica se a mensagem esperada foi retornada
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O nome é obrigatório!"))
        );
    }

    // Testa se o nome maior que o permitido é invalidado
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

    // Testa se a capacidade não pode ser negativa
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

    // Testa se a capacidade é obrigatória (não pode ser null)
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

    // Testa se os recursos não podem ser vazios
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

    // Testa se os recursos ultrapassam o tamanho permitido
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

    // Testa se o tipo da sala é obrigatório
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

    // Testa se o bloco da sala é obrigatório
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
