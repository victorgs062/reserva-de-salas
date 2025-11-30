package com.academico.api.service;

import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.dto.UsuarioResponseDTO;
import com.academico.api.model.TipoUsuario;
import com.academico.api.model.Usuario;
import com.academico.api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponseDTO> listarProfessores(){
        return usuarioRepository.findByTipoUsuario(TipoUsuario.PROFESSOR).stream()
                .map(this::toResponseDTO).toList();
    }

    public Optional<UsuarioResponseDTO> obterPorId(int id) {
        return usuarioRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        String senhaCodificada = passwordEncoder.encode(dto.senha());

        Usuario usuario = new Usuario(dto.nome(), dto.email(), senhaCodificada, dto.tipoUsuario());

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    public Optional<UsuarioResponseDTO> atualizar(int id, UsuarioRequestDTO dto) {
        return usuarioRepository.findById(id).map(usuario -> {

            String senha = usuario.getSenha();

            if (dto.senha() != null && !dto.senha().isEmpty()) {
                senha = passwordEncoder.encode(dto.senha());
            }

            Usuario temp = new Usuario(dto.nome(), dto.email(), senha, dto.tipoUsuario());

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