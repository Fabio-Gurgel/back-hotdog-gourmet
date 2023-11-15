package com.example.backhotdoggourmet.promocao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.backhotdoggourmet.exceptions.ResourceNotFoundException;

public class PromocaoService {

    @Autowired
    PromocaoRepository promocaoRepository;

    public List<Promocao> getAllPromocoes() {
        return promocaoRepository.findAll();
    }

    public Promocao getPromocaoById(Long id) {
        Optional<Promocao> promocaoEncontrada = promocaoRepository.findById(id);

        if(promocaoEncontrada.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar a promoção com id " + id);
        }

        return promocaoEncontrada.get();
    }

    public Promocao createPromocao(Promocao promocao) {
        return promocaoRepository.save(promocao);
    }

    public Promocao updatePromocao(Long id, Promocao promocao) {
        Promocao promocaoExistente = getPromocaoById(id);

        promocaoExistente.setNome(promocao.getNome());
        promocaoExistente.setDescricao(promocao.getDescricao());
        promocaoExistente.setPreco(promocao.getPreco());
        promocaoExistente.setLanche(promocao.getLanche());

        return promocaoRepository.save(promocaoExistente);
    }

    public void deletePromocao(Long id) {
        getPromocaoById(id);
        promocaoRepository.deleteById(id);
    }
    
}
