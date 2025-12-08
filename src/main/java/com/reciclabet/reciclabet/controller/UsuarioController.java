package com.reciclabet.reciclabet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.reciclabet.reciclabet.DTO.LoginRequest;
import com.reciclabet.reciclabet.model.Usuario;
import com.reciclabet.reciclabet.service.UsuarioService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ================== ADMIN / USUÁRIO ==================

    // Criar um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCriado = usuarioService.addUsuario(usuario);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }

    // Listar todos os usuários (admin)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    // Buscar usuário por ID (admin)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.buscarUsuarioPorId(id), HttpStatus.OK);
    }

    // Atualizar usuário (admin ou dono)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return new ResponseEntity<>(usuarioService.atualizarUsuario(id, usuarioAtualizado), HttpStatus.OK);
    }

    // Deletar usuário (admin)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // ================== APROVAR / REJEITAR ==================

    // Aprovar usuário (admin)
    @PutMapping("/{id}/aprovar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> aprovarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.aprovarUsuario(id));
    }

    // Rejeitar usuário (admin)
    @PutMapping("/{id}/rejeitar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> rejeitarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.rejeitarUsuario(id));
    }

    // ================== PERFIL DO USUÁRIO ==================

    // Atualizar perfil (usuário logado)
    @PutMapping("/me")
    public ResponseEntity<Usuario> atualizarPerfil(@RequestBody Usuario dados, Principal principal) {
        return ResponseEntity.ok(usuarioService.atualizarPerfil(dados, principal.getName()));
    }

    // Alterar senha (usuário logado)
    @PutMapping("/me/senha")
    public ResponseEntity<String> alterarSenha(@RequestBody Map<String, String> body, Principal principal) {
        usuarioService.alterarSenha(principal.getName(), body.get("novaSenha"));
        return ResponseEntity.ok("Senha alterada com sucesso");
    }

    // ================== LOGIN SIMPLES ==================

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String senha = loginRequest.getSenha();

        // Exemplo básico: admin hardcoded
        if ("admin@teste.com".equals(email) && "admin123".equals(senha)) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }
    }
}

