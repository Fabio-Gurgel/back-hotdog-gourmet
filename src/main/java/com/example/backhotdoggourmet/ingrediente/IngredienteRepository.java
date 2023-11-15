package com.example.backhotdoggourmet.ingrediente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

    Ingrediente findByNome(String nome);
}
