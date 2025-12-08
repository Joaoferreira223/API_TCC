package com.reciclabet.reciclabet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reciclabet.reciclabet.model.Meta;
import com.reciclabet.reciclabet.repository.MetasRepository;
import com.reciclabet.reciclabet.Config.ResourceNotFoundException;

import java.util.List;

@Service
public class MetaService {

    private final MetasRepository metasRepository;

    @Autowired
    public MetaService(MetasRepository metasRepository) {
        this.metasRepository = metasRepository;
    }

    // Criar uma nova Meta
    public Meta createMeta(Meta meta) {
        return metasRepository.save(meta);
    }

    // Listar todas as Metas
    public List<Meta> getAllMetas() {
        return metasRepository.findAll();
    }

    // Buscar uma Meta por ID com tratamento de exceção
    public Meta getMetaById(Long id) {
        return metasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meta não encontrada com ID: " + id));
    }

    // Atualizar uma Meta existente
    public Meta updateMeta(Long id, Meta metaDetails) {
        Meta existingMeta = getMetaById(id); // já lança exceção se não existir
        existingMeta.setNome(metaDetails.getNome());
        existingMeta.setDataInicio(metaDetails.getDataInicio());
        existingMeta.setDataFim(metaDetails.getDataFim());
        existingMeta.setQuantidade(metaDetails.getQuantidade());
        existingMeta.setCor(metaDetails.getCor());

        return metasRepository.save(existingMeta);
    }

    // Deletar uma Meta por ID
    public void deleteMeta(Long id) {
        Meta meta = getMetaById(id); // garante que a meta existe antes de deletar
        metasRepository.delete(meta);
    }
}
