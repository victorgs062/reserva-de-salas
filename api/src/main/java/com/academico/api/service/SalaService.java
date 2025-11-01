package com.academico.api.service;

import com.academico.api.dto.SalaRequestDTO;
import com.academico.api.dto.SalaResponseDTO;
import com.academico.api.model.Sala;
import com.academico.api.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository){
        this.salaRepository = salaRepository;
    }

    public SalaResponseDTO criar(SalaRequestDTO dto) {
        Sala sala = new Sala(dto);
        Sala salva = salaRepository.save(sala);
        return toResponseDTO(salva);
    }

    public List<SalaResponseDTO> listar() {
        return salaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SalaResponseDTO buscarPorId(int id) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        return toResponseDTO(sala);
    }

    public SalaResponseDTO atualizar(int id, SalaRequestDTO dto) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        sala.atualizar(dto);
        Sala atualizada = salaRepository.save(sala);
        return toResponseDTO(atualizada);
    }

    public void deletar(int id) {
        if (!salaRepository.existsById(id)) {
            throw new RuntimeException("Sala não encontrada");
        }
        salaRepository.deleteById(id);
    }

    private SalaResponseDTO toResponseDTO(Sala sala) {
        return new SalaResponseDTO(
                sala.getId_sala(),
                sala.getNome(),
                sala.getCapacidade(),
                sala.getRecursos(),
                sala.getTipoSala(),
                sala.getTipoSalaBloco()
        );
    }
}
