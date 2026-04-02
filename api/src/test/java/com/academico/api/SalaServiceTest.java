package com.academico.api;

import com.academico.api.dto.SalaRequestDTO;
import com.academico.api.dto.SalaResponseDTO;
import com.academico.api.model.Sala;
import com.academico.api.model.TipoSala;
import com.academico.api.model.TipoSalaBloco;
import com.academico.api.repository.SalaRepository;
import com.academico.api.service.SalaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Teste unitário da camada de Service
// Utiliza Mockito para isolar o repository
@ExtendWith(MockitoExtension.class)
class SalaServiceTest {

    @Mock
    private SalaRepository salaRepository;

    @InjectMocks
    private SalaService salaService;

    // =========================
    // TESTES DO METODO CRIAR
    // =========================

    @Test
    void deveCriarSalaComSucesso() {
        // Arrange
        SalaRequestDTO dto = new SalaRequestDTO("Sala 1", 50, "Projetor", TipoSala.ATIVA, TipoSalaBloco.A);
        Sala sala = new Sala(dto);

        when(salaRepository.save(any(Sala.class))).thenReturn(sala);

        // Act
        SalaResponseDTO response = salaService.criar(dto);

        // Assert
        assertNotNull(response);
        verify(salaRepository, times(1)).save(any(Sala.class));
    }

    // =========================
    // TESTES DO METODO LISTAR
    // =========================

    @Test
    void deveListarSalasComSucesso() {
        // Arrange
        Sala sala = new Sala(new SalaRequestDTO("Sala 1", 50, "Projetor", TipoSala.ATIVA, TipoSalaBloco.A));

        when(salaRepository.findAll()).thenReturn(List.of(sala));

        // Act
        List<SalaResponseDTO> lista = salaService.listar();

        // Assert
        assertFalse(lista.isEmpty());
        assertEquals(1, lista.size());
    }

    // =========================
    // TESTES DO METODO BUSCAR POR ID
    // =========================

    @Test
    void deveBuscarSalaPorIdComSucesso() {
        // Arrange
        Sala sala = new Sala(new SalaRequestDTO("Sala 1", 50, "Projetor", TipoSala.ATIVA, TipoSalaBloco.A));

        when(salaRepository.findById(1)).thenReturn(Optional.of(sala));

        // Act
        SalaResponseDTO response = salaService.buscarPorId(1);

        // Assert
        assertNotNull(response);
    }

    @Test
    void deveLancarErroQuandoSalaNaoEncontrada() {
        // Arrange
        when(salaRepository.findById(1)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> salaService.buscarPorId(1));
    }

    // =========================
    // TESTES DO METODO ATUALIZAR
    // =========================

    @Test
    void deveAtualizarSalaComSucesso() {
        // Arrange
        Sala sala = new Sala(new SalaRequestDTO("Sala 1", 50, "Projetor", TipoSala.ATIVA, TipoSalaBloco.A));
        SalaRequestDTO novoDto = new SalaRequestDTO("Sala Atualizada", 60, "TV", TipoSala.ATIVA, TipoSalaBloco.B);

        when(salaRepository.findById(1)).thenReturn(Optional.of(sala));
        when(salaRepository.save(any(Sala.class))).thenReturn(sala);

        // Act
        SalaResponseDTO response = salaService.atualizar(1, novoDto);

        // Assert
        assertNotNull(response);
        verify(salaRepository).save(any(Sala.class));
    }

    @Test
    void deveLancarErroAoAtualizarSalaInexistente() {
        // Arrange
        SalaRequestDTO dto = new SalaRequestDTO("Sala", 50, "Proj", TipoSala.ATIVA, TipoSalaBloco.A);

        when(salaRepository.findById(1)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(RuntimeException.class, () -> salaService.atualizar(1, dto));
    }

    // =========================
    // TESTES DO METODO DELETAR
    // =========================

    @Test
    void deveDeletarSalaComSucesso() {
        // Arrange
        when(salaRepository.existsById(1)).thenReturn(true);

        // Act
        salaService.deletar(1);

        // Assert
        verify(salaRepository).deleteById(1);
    }

    @Test
    void deveLancarErroAoDeletarSalaInexistente() {
        // Arrange
        when(salaRepository.existsById(1)).thenReturn(false);

        // Act + Assert
        assertThrows(RuntimeException.class, () -> salaService.deletar(1));
    }
}