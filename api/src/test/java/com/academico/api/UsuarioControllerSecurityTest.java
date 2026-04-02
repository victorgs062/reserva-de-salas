package com.academico.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//TESTE DE INTEGRAÇÃO (com foco em segurança)
//
//Este teste valida o comportamento da camada de segurança da aplicação
//ao acessar um endpoint protegido sem autenticação.
//
//Ferramentas utilizadas:
//MockMvc: simula requisições HTTP sem precisar subir servidor real
//
//Objetivo:
//Garantir que endpoints protegidos retornem HTTP 401 (Unauthorized)
//quando o usuário não estiver autenticado
@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerSecurityTest {

    // MockMvc é usado para simular chamadas HTTP (GET, POST, etc.)
    // diretamente no contexto da aplicação.
    @Autowired
    private MockMvc mockMvc;

    // Testa o acesso ao endpoint /usuarios sem autenticação.
    //
    // Cenário:
    // Usuário não autenticado tenta acessar recurso protegido
    //
    // Resultado esperado:
    // HTTP 401 Unauthorized
    @Test
    void deveRetornar401QuandoNaoAutenticado() throws Exception {
        // Executa uma requisição GET para o endpoint protegido
        mockMvc.perform(get("/usuarios"))

                // Valida se o status HTTP retornado é 401 (não autorizado)
                .andExpect(status().isUnauthorized())

                // Valida os campos do JSON retornado pela API
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.error").value("Unauthorized"))
                .andExpect(jsonPath("$.message").value("Usuario nao esta logado"))
                .andExpect(jsonPath("$.path").value("/usuarios"));
    }
}