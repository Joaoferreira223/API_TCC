package com.reciclabet.reciclabet.controller;


   

import com.reciclabet.reciclabet.model.Meta;
import com.reciclabet.reciclabet.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metas")
public class MetaController {

    private final MetaService metaService;

    @Autowired
    public MetaController(MetaService metaService) {
        this.metaService = metaService;
    }

    // Get all metas
    @GetMapping
    public List<Meta> getAllMetas() {
        return metaService.getAllMetas();
    }

    // Get meta by ID
    @GetMapping("/{id}")
    public ResponseEntity<Meta> getMetaById(@PathVariable Long id) {
        try {
            Meta meta = (Meta) metaService.getMetaById(id);
            return ResponseEntity.ok(meta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new meta
    @PostMapping
    public Meta createMeta(@RequestBody Meta meta) {
        return metaService.createMeta(meta);
    }

    // Update meta by ID
    @PutMapping("/{id}")
    public ResponseEntity<Meta> updateMeta(@PathVariable Long id, @RequestBody Meta metaDetails) {
        try {
            Meta updatedMeta = (Meta) metaService.updateMeta(id, metaDetails);
            return ResponseEntity.ok(updatedMeta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete meta by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeta(@PathVariable Long id) {
        try {
            metaService.deleteMeta(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


