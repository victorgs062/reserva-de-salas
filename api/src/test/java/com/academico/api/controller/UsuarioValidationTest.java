package com.academico.api.controller;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.model.TipoUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void nomeVazio(){
        UsuarioRequestDTO dto = new UsuarioRequestDTO(
          "",
          "gg21@gmail.com",
          "161125",
          TipoUsuario.ALUNO
        );

        var invalidacao = validator.validate(dto);

        assertFalse(invalidacao.isEmpty());
        assertTrue(
                invalidacao.stream().anyMatch(v -> v.getMessage().equals("O nome é obrigatório!"))
        );
    }

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
