package com.academico.api;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.model.TipoUsuario;
import static org.junit.jupiter.api.Assertions.*;


// TESTE UNITÁRIO
//
// Esta classe realiza testes unitários focados na validação do DTO (UsuarioRequestDTO),
// utilizando Bean Validation (jakarta.validation).
//
// Tipo de teste:
// Teste Unitário: testa apenas uma pequena parte do sistema (validação do DTO),
// sem subir servidor, banco de dados ou contexto Spring.
//
// Objetivo:
// Garantir que as regras de validação (anotações como @NotBlank, @Size, @Email, etc.)
// estejam funcionando corretamente.
public class UsuarioValidationTest {

    // Responsável por executar as validações do DTO
    private Validator validator;

    // Inicializa o validator antes de cada teste
    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    // Testa se o nome vazio é invalidado corretamente.
    @Test
    void nomeVazio(){
        // Cria um DTO com nome vazio
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "",
          "gg21@gmail.com",
          "161125",
          TipoUsuario.ALUNO
        );

        // Executa a validação
        var invalidacao = validator.validate(dto);

        // Verifica se houve erro
        assertFalse(invalidacao.isEmpty());

        // Verifica se a mensagem de erro esperada foi retornada
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O nome é obrigatório!"))
        );
    }

    // Testa se o nome acima do tamanho permitido é rejeitado.
    @Test
    void nomeMaiorQPermitido(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vitae pulvinar neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce id velit et nulla varius facilisis. Integer viverra, elit quis dapibus posuere, lorem erat commodo massa.",
                "gg21@gmail.com",
                "161125",
                TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Nome maior do que o permitido!"))
        );
    }

    // Testa validação de formato de e-mail inválido.
    @Test
    void emailInvalido(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "Gabriel",
          "gg21gmail.com",
          "161125",
          TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("E-mail inválido!"))
        );
    }

    // Testa e-mail vazio.
    @Test
    void emailVazio(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "Gabriel",
          "",
          "161125",
          TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O e-mail é obrigatório!"))
        );
    }

    // Testa e-mail com tamanho maior que o permitido.
    @Test
    void emailMaiorQPermitido(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "Gabriel",
          "usuarioextremamentelongoparatestesdevalidacaoqueprecisadeumemailgigantebemmaiorqueoformatonormalparaverificarseasregrasdevalidadorsobreotamanhomaximodelongitudedecamposestaoefetuandocorretaementesemqualquerproblemaounaoesperadoduranteostrabalhosdetesteautomatizado@gmail.com",
          "161125",
          TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("E-mail maior do que permitido!"))
        );
    }

    // Testa senha vazia.
    @Test
    void senhaVazia(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "Gabriel",
          "gg21@gmail.com",
          "",
           TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Digite a senha!"))
        );
    }

    // Testa senha maior que o permitido.
    @Test
    void senhaMaiorQPermitido(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "Gabriel",
          "gg21@gmail.com",
          "SenhaExtremamenteLongaParaTesteDeValidacaoQuePrecisaUltrapassarFacilmenteDuzentosECinquentaCaracteresSemConterInformacoesReaisMasSimUmMonteDeLetrasMisturadasQueNaoFazemSentidoApenasParaQueOPadroesDeComprimentoPossamSerTestadosCorretamenteDuranteAsRotinasDeTesteAutomatizadoDaAplicacaoSemQueNadaFiqueDeForaOuSejaIgnoradoPelosValidadoresImplemen",
           TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Senha maior do que permitida!"))
        );
    }

    // Testa quando o tipo de usuário não é informado (null).
    @Test
    void tipoUsuarioVazio(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Gabriel",
                "gg21@gmail.com",
                "161125",
                null
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("Informe o tipo do usuário!"))
        );
    }

}
