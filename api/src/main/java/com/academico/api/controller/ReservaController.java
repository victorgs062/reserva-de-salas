package com.academico.api.controller;

import com.academico.api.dto.ReservaRequestDTO;
import com.academico.api.dto.ReservaResponseDTO;
import com.academico.api.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin("*")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @Operation(summary = "Cria uma nova reserva")
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(
            @Parameter(description = "Dados da nova reserva")
            @RequestBody @Valid ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.criar(dto));
    }

    @Operation(summary = "Lista todas as reservas")
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listar() {
        return ResponseEntity.ok(reservaService.listar());
    }

    @Operation(summary = "Busca reserva por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscarPorId(
            @Parameter(description = "ID da reserva a ser buscada")
            @PathVariable int id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    @Operation(summary = "Atualiza uma reserva existente")
    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> atualizar(
            @Parameter(description = "ID da reserva a ser atualizada")
            @PathVariable int id,
            @Parameter(description = "Novos dados da reserva")
            @RequestBody @Valid ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.atualizar(id, dto));
    }

    @Operation(summary = "Deleta uma reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID da reserva a ser deletada")
            @PathVariable int id) {
        reservaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}