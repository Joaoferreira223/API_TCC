package com.reciclabet.reciclabet.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.reciclabet.reciclabet.model.Compra;
import com.reciclabet.reciclabet.model.ItemCompra;
import com.reciclabet.reciclabet.repository.CompraRepository;
import com.reciclabet.reciclabet.Config.ResourceNotFoundException;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    public Compra salvar(Compra compra) {
        // calcula total pago
        double total = compra.getItens()
                .stream()
                .mapToDouble(ItemCompra::getValorItem)
                .sum();
        compra.setValorTotalPago(total);

        // vincula itens à compra
        compra.getItens().forEach(item -> item.setCompra(compra));

        return compraRepository.save(compra);
    }

    // Método atualizado com tratamento de exceção
    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra com ID " + id + " não encontrada"));
    }

    public void deletar(Long id) {
        Compra compra = buscarPorId(id); // garante que o item existe antes de deletar
        compraRepository.delete(compra);
    }
}
