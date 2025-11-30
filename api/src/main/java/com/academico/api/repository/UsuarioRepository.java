package com.academico.api.repository;

import com.academico.api.model.TipoUsuario;
import com.academico.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}