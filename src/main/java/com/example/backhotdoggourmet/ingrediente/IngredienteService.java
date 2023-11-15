package com.example.backhotdoggourmet.ingrediente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backhotdoggourmet.exceptions.ResourceBadRequestException;
import com.example.backhotdoggourmet.exceptions.ResourceNotFoundException;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente getIngredienteById(Long id) {
        Optional<Ingrediente> ingredienteEncontrado = ingredienteRepository.findById(id);

        if(ingredienteEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o ingrediente com id " + id + ".");
        }

        return ingredienteEncontrado.get();
    }

    public Ingrediente createIngrediente(Ingrediente ingrediente) {

        if(ingredienteRepository.findByNome(ingrediente.getNome()) != null) {
            throw new ResourceBadRequestException("Já existe um ingrediente com esse mesmo nome.");
        }

        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente updateIngrediente(Long id, Ingrediente ingrediente) {
        Ingrediente ingredienteExistente = getIngredienteById(id);

        ingredienteExistente.setNome(ingrediente.getNome());
        ingredienteExistente.setPreco(ingrediente.getPreco());

        return ingredienteRepository.save(ingredienteExistente);
    }

    public void deleteIngrediente(Long id) {
        getIngredienteById(id);
        ingredienteRepository.deleteById(id);
    }
}
