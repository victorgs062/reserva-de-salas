package com.academico.api.dto;

import com.academico.api.model.TipoUsuario;

public record UsuarioResponseDTO(int id_usuario,
                                 String nome,
                                 String email,
                                 TipoUsuario tipoUsuario) {
}
