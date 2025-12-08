package com.reciclabet.reciclabet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reciclabet.reciclabet.model.ItensReciclaveis;
import com.reciclabet.reciclabet.repository.RepositoryItensReciclaveis;
import com.reciclabet.reciclabet.Config.ResourceNotFoundException;

import java.util.List;

@Service
public class ItensReciclaveisService {

    @Autowired
    private RepositoryItensReciclaveis itensReciclaveisRepository;

    // Criar um novo item reciclável
    public ItensReciclaveis criarItemReciclavel(ItensReciclaveis itemReciclavel) {
        return itensReciclaveisRepository.save(itemReciclavel);
    }

    // Buscar um item reciclável por ID
    public ItensReciclaveis buscarItemReciclavelPorId(Long id) {
        return itensReciclaveisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item com ID " + id + " não encontrado"));
    }

    // Atualizar informações de um item reciclável
    public ItensReciclaveis atualizarItemReciclavel(Long id, ItensReciclaveis itemAtualizado) {
        ItensReciclaveis itemExistente = buscarItemReciclavelPorId(id);

        if (itemAtualizado.getNome() != null) {
            itemExistente.setNome(itemAtualizado.getNome());
        }
        if (itemAtualizado.getMaterial() != null) {
            itemExistente.setMaterial(itemAtualizado.getMaterial());
        }
        if (itemAtualizado.getValorKilo() != null) {
            itemExistente.setValorKilo(itemAtualizado.getValorKilo());
        }
        if (itemAtualizado.getDescricao() != null) {
            itemExistente.setDescricao(itemAtualizado.getDescricao());
        }

        return itensReciclaveisRepository.save(itemExistente);
    }

    // Deletar um item reciclável
    public void deletarItemReciclavel(Long id) {
        ItensReciclaveis item = buscarItemReciclavelPorId(id);
        itensReciclaveisRepository.delete(item);
    }

    // Listar todos os itens recicláveis
    public List<ItensReciclaveis> listarItensReciclaveis() {
        return itensReciclaveisRepository.findAll();
    }

    public static List<ItensReciclaveis> listarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
    }

    public static void salvar(ItensReciclaveis item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

 
}
