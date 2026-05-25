package com.academico.api;

import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.dto.UsuarioResponseDTO;
import com.academico.api.model.TipoUsuario;
import com.academico.api.service.UsuarioService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Sobe o contexto completo da aplicação Spring Boot
@SpringBootTest

// Permite realizar requisições HTTP simuladas
@AutoConfigureMockMvc
class UsuarioControllerTest {

    // Responsável por simular chamadas HTTP na API
    @Autowired
    private MockMvc mockMvc;

    // Converte objetos Java para JSON
    @Autowired
    private ObjectMapper objectMapper;

    // Cria um mock do UsuarioService
    @MockBean
    private UsuarioService usuarioService;

    // =========================================
    // TESTE: GET /usuarios -> 200
    // =========================================
    @Test

    // Simula um usuário autenticado com perfil ADMIN
    @WithMockUser(roles = "ADMIN")
    void deveListarUsuarios() throws Exception {

        // Cria um usuário fictício para o teste
        UsuarioResponseDTO usuario =
                new UsuarioResponseDTO(
                        1,
                        "Victor",
                        "victorgs062@gmail.com",
                        TipoUsuario.PROFESSOR
                );

        // Simula o retorno do service
        when(usuarioService.listarTodos())
                .thenReturn(List.of(usuario));

        // Executa a requisição GET para /usuarios
        mockMvc.perform(get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON))

                // Verifica se o status retornado é 200
                .andExpect(status().isOk())

                // Verifica se o retorno é uma lista
                .andExpect(jsonPath("$").isArray())

                // Verifica se o nome do primeiro usuário é Victor
                .andExpect(jsonPath("$[0].nome")
                        .value("Victor"));
    }

    // =========================================
    // TESTE: GET /usuarios/1 -> 200
    // =========================================
    @Test
    @WithMockUser(roles = "ADMIN")
    void deveBuscarUsuarioPorId() throws Exception {

        // Cria um usuário fictício
        UsuarioResponseDTO usuario =
                new UsuarioResponseDTO(
                        1,
                        "Victor",
                        "victorgs062@gmail.com",
                        TipoUsuario.PROFESSOR
                );

        // Simula busca do usuário pelo ID
        when(usuarioService.obterPorId(1))
                .thenReturn(Optional.of(usuario));

        // Executa requisição GET
        mockMvc.perform(get("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))

                // Verifica se o status retornado é 200
                .andExpect(status().isOk())

                // Verifica se o nome está correto
                .andExpect(jsonPath("$.nome")
                        .value("Victor"))

                // Verifica se o email está correto
                .andExpect(jsonPath("$.email")
                        .value("victorgs062@gmail.com"));
    }

    // =========================================
    // TESTE: GET /usuarios/999 -> 404
    // =========================================
    @Test
    @WithMockUser(roles = "ADMIN")
    void deveRetornar404AoBuscarUsuarioInexistente() throws Exception {

        // Simula usuário não encontrado
        when(usuarioService.obterPorId(999))
                .thenReturn(Optional.empty());

        // Executa requisição GET
        mockMvc.perform(get("/usuarios/999")
                        .contentType(MediaType.APPLICATION_JSON))

                // Verifica se o retorno foi 404
                .andExpect(status().isNotFound());
    }

    // =========================================
    // TESTE: POST /usuarios -> 201
    // =========================================
    @Test
    @WithMockUser(roles = "ADMIN")
    void deveCriarUsuario() throws Exception {

        // Objeto enviado na requisição
        UsuarioRequestDTO request =
                new UsuarioRequestDTO(
                        "Victor",
                        "victorgs062@gmail.com",
                        "123456",
                        TipoUsuario.ALUNO
                );

        // Objeto esperado como resposta
        UsuarioResponseDTO response =
                new UsuarioResponseDTO(
                        1,
                        "Victor",
                        "victorgs062@gmail.com",
                        TipoUsuario.ALUNO
                );

        // Simula criação do usuário
        when(usuarioService.criar(any(UsuarioRequestDTO.class)))
                .thenReturn(response);

        // Executa requisição POST
        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)

                        // Converte objeto Java para JSON
                        .content(objectMapper.writeValueAsString(request)))

                // Verifica se o status retornado é 201
                .andExpect(status().isCreated())

                // Verifica se o nome retornado está correto
                .andExpect(jsonPath("$.nome")
                        .value("Victor"))

                // Verifica se o email retornado está correto
                .andExpect(jsonPath("$.email")
                        .value("victorgs062@gmail.com"));
    }

    // =========================================
    // TESTE: DELETE /usuarios/1 -> 204
    // =========================================
    @Test
    @WithMockUser(roles = "ADMIN")
    void deveDeletarUsuario() throws Exception {

        // Simula exclusão com sucesso
        when(usuarioService.deletar(1))
                .thenReturn(true);

        // Executa requisição DELETE
        mockMvc.perform(delete("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON))

                // Verifica se o status retornado é 204
                .andExpect(status().isNoContent());
    }
}