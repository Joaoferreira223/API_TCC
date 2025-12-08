package com.reciclabet.reciclabet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reciclabet.reciclabet.model.ItensReciclaveis;

@Repository
public interface RepositoryItensReciclaveis extends JpaRepository<ItensReciclaveis, Long> {
    // Nenhum método adicional é necessário, JpaRepository já fornece:
    // findAll(), findById(Long id), save(ItensReciclaveis entity), deleteById(Long id)...
}
