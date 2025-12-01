package com.academico.api.service;

import com.academico.api.dto.ReservaRequestDTO;
import com.academico.api.dto.ReservaResponseDTO;
import com.academico.api.model.*;
import com.academico.api.repository.DisciplinaRepository;
import com.academico.api.repository.ReservaRepository;
import com.academico.api.repository.SalaRepository;
import com.academico.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;
    private final DisciplinaRepository disciplinaRepository;

    public ReservaService(
            ReservaRepository reservaRepository,
            UsuarioRepository usuarioRepository,
            SalaRepository salaRepository,
            DisciplinaRepository disciplinaRepository
    ) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.salaRepository = salaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public ReservaResponseDTO criar(ReservaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.id_usuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Sala sala = salaRepository.findById(dto.id_sala())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(dto.id_disciplina())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        boolean reservaExistente = reservaRepository.verificarDiaReservado(dto.id_sala(), dto.data(), dto.dataHoraInicio(), dto.dataHoraFim());
        boolean professorReservado = reservaRepository.verificarProfessor(dto.id_usuario(), dto.data(), dto.dataHoraInicio(), dto.dataHoraFim());

        if(reservaExistente == true){
            throw new RuntimeException("Sala já reservada!");
        }
        if (professorReservado == true){
            throw new RuntimeException("Professor já reservado para uma aula!");
        }
        if (sala.getTipoSala() == TipoSala.INATIVA){
            throw new RuntimeException("Sala inativa, escolha uma ativa!");
        }

        Reserva reserva = new Reserva(dto, usuario, sala, disciplina);

        reservaRepository.save(reserva);

        return toResponseDTO(reserva);
    }

    public List<ReservaResponseDTO> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ReservaResponseDTO buscarPorId(int id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return toResponseDTO(reserva);
    }

    public ReservaResponseDTO atualizar(int id, ReservaRequestDTO dto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.id_usuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Sala sala = salaRepository.findById(dto.id_sala())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        Disciplina disciplina = disciplinaRepository.findById(dto.id_disciplina())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        reserva.atualizar(dto, usuario, sala, disciplina);

        reservaRepository.save(reserva);

        return toResponseDTO(reserva);
    }

    public void deletar(int id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        reservaRepository.delete(reserva);
    }

    private ReservaResponseDTO toResponseDTO(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId_reserva(),
                reserva.getData(),
                reserva.getDataHoraInicio(),
                reserva.getDataHoraFim(),
                reserva.getStatus(),
                reserva.getUsuario().getNome(),
                reserva.getSala().getNome(),
                reserva.getDisciplina().getNome(),
                reserva.getSala().getId_sala(),
                reserva.getSala().getTipoSalaBloco(),
                reserva.getUsuario().getId_usuario(),
                reserva.getDisciplina().getId_disciplina()

        );
    }
}