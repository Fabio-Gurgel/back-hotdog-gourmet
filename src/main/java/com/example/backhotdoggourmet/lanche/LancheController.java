package com.example.backhotdoggourmet.lanche;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lanches")
@CrossOrigin
public class LancheController {

    @Autowired
    private LancheService lancheService;

    @GetMapping
    public List<Lanche> getAllLanches() {
        return lancheService.getAllLanches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lanche> getLancheById(@PathVariable Long id) {
        Lanche lancheEncontrado = lancheService.getLancheById(id);
        return ResponseEntity.ok(lancheEncontrado);
    }

    @PostMapping
    public ResponseEntity<Lanche> createLanche(@RequestBody Lanche lanche) {
        Lanche lancheCriado = lancheService.createLanche(lanche);
        return ResponseEntity.status(HttpStatus.CREATED).body(lancheCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lanche> updateLanche(@PathVariable Long id, @RequestBody Lanche lanche) {
        Lanche lancheEditado = lancheService.updateLanche(id, lanche);
        return ResponseEntity.ok(lancheEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanche(@PathVariable Long id) {
        lancheService.deleteLanche(id);
        return ResponseEntity.noContent().build();
    }
}
