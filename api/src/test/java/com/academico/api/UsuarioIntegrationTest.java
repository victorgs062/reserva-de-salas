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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class UsuarioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void deveBuscarUsuariosNoBanco() throws Exception {

        // Cria um usuário para salvar no banco
        Usuario usuario = new Usuario();

        usuario.setNome("Victor");
        usuario.setEmail("victor@email.com");
        usuario.setSenha("123456");
        usuario.setTipoUsuario(TipoUsuario.ADMIN);

        // Salva o usuário no banco
        usuarioRepository.save(usuario);

        // Faz a chamada GET na API
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome")
                        .value("Victor"));
    }
}