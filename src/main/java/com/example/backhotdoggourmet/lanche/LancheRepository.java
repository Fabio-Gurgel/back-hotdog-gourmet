package com.example.backhotdoggourmet.lanche;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancheRepository extends JpaRepository<Lanche, Long> {

    Lanche findByNome(String nome);
}
