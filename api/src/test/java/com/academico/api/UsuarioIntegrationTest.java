package com.academico.api;

import com.academico.api.model.TipoUsuario;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;

import com.academico.api.model.Usuario;
import com.academico.api.repository.UsuarioRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Inicializa o contexto completo da aplicação
@SpringBootTest

// Configura o MockMvc para simular requisições HTTP
@AutoConfigureMockMvc

// Garante rollback após o teste
@Transactional

// Usa o perfil de teste da aplicação
@ActiveProfiles("test")
class UsuarioIntegrationTest {

    // Permite simular requisições para a API
    @Autowired
    private MockMvc mockMvc;

    // Acesso ao repositório de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Define um metodo de teste
    @Test

    // Simula um usuário autenticado com perfil ADMIN
    @WithMockUser(roles = "ADMIN")
    void deveBuscarUsuariosNoBanco() throws Exception {

        // Cria um usuário para salvar no banco
        Usuario usuario = new Usuario();

        // Define o nome do usuário
        usuario.setNome("Victor");

        // Define o email do usuário
        usuario.setEmail("victor@email.com");

        // Define a senha do usuário
        usuario.setSenha("123456");

        // Define o tipo do usuário
        usuario.setTipoUsuario(TipoUsuario.ADMIN);

        // Salva o usuário no banco de dados
        usuarioRepository.save(usuario);

        // Faz uma requisição GET para o endpoint /usuarios
        mockMvc.perform(get("/usuarios"))

                // Verifica se o status retornado é 200 OK
                .andExpect(status().isOk())

                // Verifica se o nome do primeiro usuário é Victor
                .andExpect(jsonPath("$[0].nome")
                        .value("Victor"));
    }
}