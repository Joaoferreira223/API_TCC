package com.reciclabet.reciclabet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.reciclabet.reciclabet.model.Meta;

@Repository
public interface MetasRepository extends JpaRepository<Meta, Long> {
    // Nenhum método extra necessário — JpaRepository já traz tudo:
    // - findAll()
    // - findById()
    // - save()
    // - deleteById()
}
