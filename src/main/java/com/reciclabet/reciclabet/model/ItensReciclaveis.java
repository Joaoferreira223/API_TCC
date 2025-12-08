package com.reciclabet.reciclabet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_reciclaveis")
public class ItensReciclaveis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "material", nullable = false)
    private String material;

    @Column(name = "valor_kilo", nullable = false)
    private Double valorKilo;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Double getValorKilo() { return valorKilo; }
    public void setValorKilo(Double valorKilo) { this.valorKilo = valorKilo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
