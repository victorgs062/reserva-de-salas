package com.academico.api.controller;

import com.academico.api.dto.DisciplinaRequestDTO;
import com.academico.api.dto.DisciplinaResponseDTO;
import com.academico.api.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("disciplinas")
@CrossOrigin("*")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @Operation(summary = "Lista todas as disciplinas cadastradas")
    @GetMapping
    public ResponseEntity<List<DisciplinaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(disciplinaService.listarTodas());
    }

    @Operation(summary = "Listar disciplinas do professor")
    @GetMapping("/por-professor/{idProfessor}")
    public ResponseEntity<List<DisciplinaResponseDTO>> listarPorProfessor(@PathVariable int idProfessor){
        return ResponseEntity.ok(disciplinaService.listarDisciplinaDoProfessor(idProfessor));
    }

    @Operation(summary = "Busca uma disciplina pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@Parameter(description = "ID da disciplina a ser consultada") @PathVariable int id) {
        return ResponseEntity.ok(disciplinaService.buscarPorId(id));
    }

    @Operation(summary = "Cria uma nova disciplina e associa a um professor")
    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> criar(@Parameter(description = "Dados da nova disciplina, incluindo o ID do professor responsável") @RequestBody @Valid DisciplinaRequestDTO dto) {
        return ResponseEntity.ok(disciplinaService.criarDisciplina(dto));
    }

    @Operation(summary = "Atualiza os dados de uma disciplina existente")
    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(@Parameter(description = "ID da disciplina que será atualizada") @PathVariable int id, @Parameter(description = "Novos dados da disciplina, incluindo o ID do professor responsável") @RequestBody @Valid DisciplinaRequestDTO dto) {
        return ResponseEntity.ok(disciplinaService.atualizarDisciplina(id, dto));
    }

    @Operation(summary = "Deleta uma disciplina pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID da disciplina a ser deletada") @PathVariable int id) {
        disciplinaService.deletarDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}
