package com.academico.api.controller;

import com.academico.api.dto.SalaRequestDTO;
import com.academico.api.dto.SalaResponseDTO;
import com.academico.api.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/salas")
@CrossOrigin("*")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService){
        this.salaService = salaService;
    }

    @Operation(summary = "Cria salas a partir de um arquivo Excel")
    @PostMapping(value = "/importar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> criarComExcel(
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(salaService.criarComExcel(file));
    }

    @Operation(summary = "Cria uma nova sala")
    @PostMapping
    public ResponseEntity<SalaResponseDTO> criar(@Parameter(description = "Dados da nova sala") @RequestBody SalaRequestDTO dto){
        SalaResponseDTO novaSala = salaService.criar(dto);
        return ResponseEntity.ok(novaSala);
    }

    @Operation(summary = "Lista todas as salas")
    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> listar() {
        return ResponseEntity.ok(salaService.listar());
    }

    @Operation(summary = "Busca uma sala por ID")
    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@Parameter(description = "ID da sala") @PathVariable int id) {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @Operation(summary = "Atualiza os dados de uma sala")
    @PutMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> atualizar(@Parameter(description = "ID da sala a ser atualizada")  @PathVariable int id, @Parameter(description = "Novos dados da sala")  @RequestBody SalaRequestDTO dto) {
        return ResponseEntity.ok(salaService.atualizar(id, dto));
    }

    @Operation(summary = "Deleta uma sala por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID da sala a ser deletada") @PathVariable int id) {
        salaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
