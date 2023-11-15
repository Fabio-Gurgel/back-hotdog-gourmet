package com.example.backhotdoggourmet.lanche;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backhotdoggourmet.exceptions.ResourceNotFoundException;
import com.example.backhotdoggourmet.ingrediente.Ingrediente;
import com.example.backhotdoggourmet.ingrediente.IngredienteService;

@Service
public class LancheService {
    
    @Autowired
    LancheRepository lancheRepository;

    @Autowired
    IngredienteService ingredienteService;

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
        double precoDoLanche = 0;
        List<Ingrediente> ingredientesDoLanche = new ArrayList<>();

        for(Ingrediente ingrediente : lanche.getIngredientes()) {
            ingredientesDoLanche.add(ingredienteService.getIngredienteById(ingrediente.getId()));
        }

        lanche.setIngredientes(ingredientesDoLanche);

        for(Ingrediente ingrediente : lanche.getIngredientes()) {
            precoDoLanche += ingrediente.getPreco();
        }
        lanche.setPreco(precoDoLanche);
        
        return lancheRepository.save(lanche);
    }

    public Lanche updateLanche(Long id, Lanche lanche) {
        Lanche lancheExistente = getLancheById(id);

        double precoDoLanche = 0;

        for(Ingrediente ingrediente : lanche.getIngredientes()) {
            precoDoLanche += ingrediente.getPreco();
        }

        lancheExistente.setNome(lanche.getNome());
        lancheExistente.setPreco(precoDoLanche);
        lancheExistente.setIngredientes(lanche.getIngredientes());

        return lancheRepository.save(lancheExistente);
    }

    public void deleteLanche(Long id) {
        getLancheById(id);
        lancheRepository.deleteById(id);
    }
}
