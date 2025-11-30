package com.academico.api.controller;

import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.dto.UsuarioResponseDTO;
import com.academico.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Lista todos os usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @Operation(summary = "Listar os professores")
    @GetMapping("/professores")
    public ResponseEntity<List<UsuarioResponseDTO>> listarProfessores() {
        return ResponseEntity.ok(usuarioService.listarProfessores());
    }

    @Operation(summary = "Obtém usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obterPorId(@Parameter(description = "ID do usuario que deseja consultar") @PathVariable int id) {
        return usuarioService.obterPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo usuário")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@Parameter(description = "Dados do usuário que será criado") @RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO criado = usuarioService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @Operation(summary = "Atualiza um usuário existente")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@Parameter(description = "ID do usuário que será atualizado") @PathVariable int id, @Parameter(description = "Novos dados do usuário") @RequestBody @Valid UsuarioRequestDTO dto) {
        return usuarioService.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID do usuário que será deletado") @PathVariable int id) {
        boolean deletado = usuarioService.deletar(id);
        return deletado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
