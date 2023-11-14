package com.example.backhotdoggourmet.ingrediente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteService.getAllIngredientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable Long id) {
        Ingrediente ingrediente = ingredienteService.getIngredienteById(id);
        return ResponseEntity.ok(ingrediente);
    }

    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente createdIngrediente = ingredienteService.createIngrediente(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIngrediente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        Ingrediente updatedIngrediente = ingredienteService.updateIngrediente(id, ingrediente);
        return ResponseEntity.ok(updatedIngrediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        ingredienteService.deleteIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
