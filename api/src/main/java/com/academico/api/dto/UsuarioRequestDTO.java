package com.academico.api.dto;

import com.academico.api.model.TipoUsuario;
import jakarta.validation.constraints.*;


public record UsuarioRequestDTO(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(max = 250, message = "Nome maior do que o permitido!")
        String nome,
        @Email(message = "E-mail inválido!")
        @NotBlank(message = "O e-mail é obrigatório!")
        @Size(max = 250, message = "E-mail maior do que permitido!")
        String email,
        @Size(max = 250, message = "Senha maior do que permitida!")
        @NotBlank(message = "Digite a senha!")
        //Depois pode usar o @Pattern, mas por enquanto deve ser mais facil testar sem
        String senha,
        @NotNull(message = "Informe o tipo do usuário!")
        TipoUsuario tipoUsuario) {
}