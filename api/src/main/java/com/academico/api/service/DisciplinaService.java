package com.academico.api.service;

import com.academico.api.dto.DisciplinaRequestDTO;
import com.academico.api.dto.DisciplinaResponseDTO;
import com.academico.api.model.Disciplina;
import com.academico.api.model.TipoUsuario;
import com.academico.api.model.Usuario;
import com.academico.api.repository.DisciplinaRepository;
import com.academico.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final UsuarioRepository usuarioRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, UsuarioRepository usuarioRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<DisciplinaResponseDTO> listarTodas() {
        return disciplinaRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public DisciplinaResponseDTO buscarPorId(int id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        return converterParaResponse(disciplina);
    }

    public DisciplinaResponseDTO criarDisciplina(DisciplinaRequestDTO dto) {
        Usuario professor = usuarioRepository.findById(dto.id_professor())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (professor.getTipoUsuario() != TipoUsuario.PROFESSOR) {
            throw new RuntimeException("A disciplina só pode ser cadastrada para um usuário do tipo PROFESSOR.");
        }

        Disciplina disciplina = new Disciplina(
                dto.nome(),
                dto.codigo(),
                dto.qtd_alunos(),
                professor
        );

        disciplinaRepository.save(disciplina);
        return converterParaResponse(disciplina);
    }

    public DisciplinaResponseDTO atualizarDisciplina(int id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        Usuario professor = usuarioRepository.findById(dto.id_professor())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (professor.getTipoUsuario() != TipoUsuario.PROFESSOR) {
            throw new RuntimeException("Somente usuários do tipo PROFESSOR podem ser atribuídos a uma disciplina.");
        }

        disciplina.atualizarDados(dto.nome(), dto.codigo(), dto.qtd_alunos(), professor);

        disciplinaRepository.save(disciplina);
        return converterParaResponse(disciplina);
    }

    public void deletarDisciplina(int id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        disciplinaRepository.delete(disciplina);
    }

    private DisciplinaResponseDTO converterParaResponse(Disciplina disciplina) {
        return new DisciplinaResponseDTO(
                disciplina.getId_disciplina(),
                disciplina.getNome(),
                disciplina.getCodigo(),
                disciplina.getQtd_alunos(),
                disciplina.getProfessor().getId_usuario()
        );
    }
}
