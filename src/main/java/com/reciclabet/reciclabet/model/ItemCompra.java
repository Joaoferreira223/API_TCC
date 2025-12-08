package com.reciclabet.reciclabet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_compra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double peso;
    private Double valorItem;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ItensReciclaveis produto;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getValorItem() { return valorItem; }
    public void setValorItem(Double valorItem) { this.valorItem = valorItem; }

    public ItensReciclaveis getProduto() { return produto; }
    public void setProduto(ItensReciclaveis produto) { this.produto = produto; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
}
