package com.reciclabet.reciclabet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.reciclabet.reciclabet.model.Compra;
import com.reciclabet.reciclabet.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras() {
        return ResponseEntity.ok(compraService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<Compra> criarCompra(@RequestBody Compra compra) {
        return ResponseEntity.ok(compraService.salvar(compra));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarCompraPorId(@PathVariable Long id) {
        Compra compra = compraService.buscarPorId(id);
        return compra != null ? ResponseEntity.ok(compra) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        compraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
