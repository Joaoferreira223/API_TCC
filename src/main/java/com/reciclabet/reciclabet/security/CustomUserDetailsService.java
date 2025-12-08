package com.reciclabet.reciclabet.security;

import com.reciclabet.reciclabet.model.Usuario;
import com.reciclabet.reciclabet.repository.RepositoryBet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RepositoryBet betRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //  Buscar o usuário no banco
        Usuario usuario = betRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        //  Retornar UserDetails com Spring Security
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha()) // senha precisa estar em BCrypt
                .roles(usuario.getRole())     // ADMIN ou USER
                .disabled(!usuario.isAtivo()) // bloqueio se inativo
                .build();
    }
}
