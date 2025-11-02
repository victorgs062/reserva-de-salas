package com.academico.api.dto;

import com.academico.api.model.TipoUsuario;

public record UsuarioRequestDTO(String nome,
                                String email,
                                String senha,
                                TipoUsuario tipoUsuario) {
}