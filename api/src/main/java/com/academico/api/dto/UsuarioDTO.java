package com.academico.api.dto;

import com.academico.api.model.TipoUsuario;

public record UsuarioDTO(String nome,
                         String email,
                         TipoUsuario tipoUsuario) {
}