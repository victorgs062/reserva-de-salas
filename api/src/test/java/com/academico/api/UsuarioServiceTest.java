package com.academico.api;

import com.academico.api.dto.UsuarioRequestDTO;
import com.academico.api.dto.UsuarioResponseDTO;
import com.academico.api.model.TipoUsuario;
import com.academico.api.model.Usuario;
import com.academico.api.repository.UsuarioRepository;

import com.academico.api.service.UsuarioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Habilita o uso do Mockito junto com o JUnit
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    // Cria um mock (objeto falso) do repositório
    @Mock
    private UsuarioRepository usuarioRepository;

    // Cria um mock do codificador de senha
    @Mock
    private PasswordEncoder passwordEncoder;

    // Injeta automaticamente os mocks dentro do UsuarioService
    @InjectMocks
    private UsuarioService usuarioService;

    // Objeto usuário utilizado nos testes
    private Usuario usuario;

    // Metodo executado antes de cada teste
    @BeforeEach
    void setup() {

        // Cria um usuário padrão para os testes
        usuario = new Usuario(
                "Victor",
                "victorgs062@gmail.com",
                "123456",
                TipoUsuario.PROFESSOR
        );
    }

    // Teste responsável por validar a listagem de usuários
    @Test
    void deveListarTodosUsuarios() {

        // Simula o retorno do banco de dados
        when(usuarioRepository.findAll())
                .thenReturn(List.of(usuario));

        // Executa o metodo do service
        List<UsuarioResponseDTO> resultado =
                usuarioService.listarTodos();

        // Verifica se a lista possui 1 usuário
        assertEquals(1, resultado.size());

        // Verifica se o nome retornado está correto
        assertEquals(
                "Victor",
                resultado.get(0).nome()
        );

        // Verifica se o metodo findAll foi chamado uma vez
        verify(usuarioRepository, times(1))
                .findAll();
    }

    // Teste responsável por validar a listagem de professores
    @Test
    void deveListarProfessores() {

        // Simula retorno do banco apenas com professores
        when(usuarioRepository.findByTipoUsuario(TipoUsuario.PROFESSOR))
                .thenReturn(List.of(usuario));

        // Executa o metodo
        List<UsuarioResponseDTO> resultado =
                usuarioService.listarProfessores();

        // Verifica se retornou um professor
        assertEquals(1, resultado.size());

        // Verifica se o tipo do usuário é PROFESSOR
        assertEquals(
                TipoUsuario.PROFESSOR,
                resultado.get(0).tipoUsuario()
        );
    }

    // Teste responsável por buscar usuário pelo ID
    @Test
    void deveObterUsuarioPorId() {

        // Simula busca do usuário no banco
        when(usuarioRepository.findById(1))
                .thenReturn(Optional.of(usuario));

        // Executa o metodo
        Optional<UsuarioResponseDTO> resultado =
                usuarioService.obterPorId(1);

        // Verifica se o usuário foi encontrado
        assertTrue(resultado.isPresent());

        // Verifica se o nome retornado está correto
        assertEquals(
                "Victor",
                resultado.get().nome()
        );
    }

    // Teste responsável pela criação de usuário
    @Test
    void deveCriarUsuario() {

        // Cria objeto DTO com os dados do usuário
        UsuarioRequestDTO dto =
                new UsuarioRequestDTO(
                        "Victor",
                        "victorgs062@gmail.com",
                        "123456",
                        TipoUsuario.ALUNO
                );

        // Simula a criptografia da senha
        when(passwordEncoder.encode("123456"))
                .thenReturn("senhaCodificada");

        // Simula salvamento no banco de dados
        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        // Executa o metodo criar
        UsuarioResponseDTO resultado =
                usuarioService.criar(dto);

        // Verifica se o objeto retornado não é nulo
        assertNotNull(resultado);

        // Verifica se o nome está correto
        assertEquals(
                "Victor",
                resultado.nome()
        );

        // Verifica se a criptografia foi executada uma vez
        verify(passwordEncoder, times(1))
                .encode("123456");

        // Verifica se o save foi executado uma vez
        verify(usuarioRepository, times(1))
                .save(any(Usuario.class));
    }

    // Teste responsável por atualizar um usuário
    @Test
    void deveAtualizarUsuario() {

        // Cria DTO com novos dados
        UsuarioRequestDTO dto =
                new UsuarioRequestDTO(
                        "Victor Atualizado",
                        "novo@email.com",
                        "novaSenha",
                        TipoUsuario.PROFESSOR
                );

        // Simula busca do usuário no banco
        when(usuarioRepository.findById(1))
                .thenReturn(Optional.of(usuario));

        // Simula criptografia da nova senha
        when(passwordEncoder.encode("novaSenha"))
                .thenReturn("senhaNovaCodificada");

        // Simula atualização no banco
        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        // Executa atualização
        Optional<UsuarioResponseDTO> resultado =
                usuarioService.atualizar(1, dto);

        // Verifica se o usuário foi atualizado
        assertTrue(resultado.isPresent());

        // Verifica se o save foi chamado
        verify(usuarioRepository, times(1))
                .save(any(Usuario.class));
    }

    // Teste responsável por deletar usuário existente
    @Test
    void deveDeletarUsuario() {

        // Simula busca do usuário
        when(usuarioRepository.findById(1))
                .thenReturn(Optional.of(usuario));

        // Executa exclusão
        boolean resultado =
                usuarioService.deletar(1);

        // Verifica se a exclusão ocorreu com sucesso
        assertTrue(resultado);

        // Verifica se o delete foi executado
        verify(usuarioRepository, times(1))
                .delete(usuario);
    }

    // Teste responsável por validar exclusão de usuário inexistente
    @Test
    void naoDeveDeletarUsuarioInexistente() {

        // Simula usuário não encontrado
        when(usuarioRepository.findById(1))
                .thenReturn(Optional.empty());

        // Executa exclusão
        boolean resultado =
                usuarioService.deletar(1);

        // Verifica se o retorno foi falso
        assertFalse(resultado);
    }
}