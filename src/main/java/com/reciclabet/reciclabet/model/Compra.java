package com.reciclabet.reciclabet.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_total_pago", nullable = false)
    private Double valorTotalPago;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itens;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getValorTotalPago() { return valorTotalPago; }
    public void setValorTotalPago(Double valorTotalPago) { this.valorTotalPago = valorTotalPago; }

    public List<ItemCompra> getItens() { return itens; }
    public void setItens(List<ItemCompra> itens) { this.itens = itens; }
    public Long getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }
    public Long getPesoTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPesoTotal'");
    }
}
