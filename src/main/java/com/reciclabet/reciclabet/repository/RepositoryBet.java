package com.reciclabet.reciclabet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reciclabet.reciclabet.model.Usuario;

import java.util.Optional;

@Repository
public interface RepositoryBet extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}


