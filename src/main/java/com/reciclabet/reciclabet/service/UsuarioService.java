package com.reciclabet.reciclabet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reciclabet.reciclabet.model.Usuario;
import com.reciclabet.reciclabet.repository.RepositoryBet;
import com.reciclabet.reciclabet.Config.ResourceNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RepositoryBet betRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return betRepository.findAll();
    }

    // Adicionar um novo usuário
    public Usuario addUsuario(Usuario usuario) {
        // Validação mínima
        if (usuario.getNome() == null || usuario.getEmail() == null || usuario.getCnpj() == null || usuario.getSenha() == null) {
            throw new RuntimeException("Todos os campos são obrigatórios: nome, email, CNPJ e senha");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); // criptografar senha
        usuario.setAtivo(false); // começa inativo até admin aprovar
        if (usuario.getRole() == null) {
            usuario.setRole("USER"); // padrão USER
        }
        return betRepository.save(usuario);
    }

    // Buscar usuário por ID
    public Usuario buscarUsuarioPorId(Long id) {
        return betRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
    }

    // Deletar usuário
    public void deleteUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        betRepository.delete(usuario);
    }

    // Atualizar usuário
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarUsuarioPorId(id);
        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }
        return betRepository.save(usuarioExistente);
    }

    // Aprovar usuário (ADMIN)
    public Usuario aprovarUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setAtivo(true);
        return betRepository.save(usuario);
    }

    // Rejeitar usuário (ADMIN)
    public Usuario rejeitarUsuario(Long id) {
        Usuario usuario = buscarUsuarioPorId(id);
        usuario.setAtivo(false);
        usuario.setRole("REJEITADO"); // opcional: marca como rejeitado
        return betRepository.save(usuario);
    }

    // Atualizar perfil (usuário logado)
    public Usuario atualizarPerfil(Usuario dados, String email) {
        Usuario usuario = betRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        return betRepository.save(usuario);
    }

    // Alterar senha (usuário logado)
    public void alterarSenha(String email, String novaSenha) {
        Usuario usuario = betRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        betRepository.save(usuario);
    }
}
