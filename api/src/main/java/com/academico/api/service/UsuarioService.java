package com.academico.api.service;

import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.dto.UsuarioResponseDTO;
import com.academico.api.model.Usuario;
import com.academico.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioResponseDTO> obterPorId(int id) {
        return usuarioRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(dto.nome(), dto.email(), dto.senha(), dto.tipoUsuario());
        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public Optional<UsuarioResponseDTO> atualizar(int id, UsuarioRequestDTO dto) {
        return usuarioRepository.findById(id).map(usuario -> {
            Usuario temp = new Usuario(dto.nome(), dto.email(), dto.senha(), dto.tipoUsuario());
            usuario.atualizar(temp);
            Usuario salvo = usuarioRepository.save(usuario);
            return toResponseDTO(salvo);
        });
    }

    public boolean deletar(int id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return true;
        }).orElse(false);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId_usuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario()
        );
    }

}
