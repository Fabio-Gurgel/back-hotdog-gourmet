package com.example.backhotdoggourmet.lanche;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backhotdoggourmet.exceptions.ResourceNotFoundException;

@Service
public class LancheService {
    
    @Autowired
    LancheRepository lancheRepository;

    public List<Lanche> getAllLanches() {
        return lancheRepository.findAll();
    }

    public Lanche getLancheById(Long id) {
        Optional<Lanche> lancheEncontrado = lancheRepository.findById(id);

        if(lancheEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o lanche com id " + id);
        }

        return lancheEncontrado.get();
    }

    public Lanche createLanche(Lanche lanche) {
        return lancheRepository.save(lanche);
    }

    public Lanche updateLanche(Long id, Lanche lanche) {
        Lanche lancheExistente = getLancheById(id);

        lancheExistente.setNome(lanche.getNome());
        lancheExistente.setPreco(lanche.getPreco());
        lancheExistente.setIngredientes(lanche.getIngredientes());

        return lancheRepository.save(lancheExistente);
    }

    public void deleteLanche(Long id) {
        getLancheById(id);
        lancheRepository.deleteById(id);
    }
}
