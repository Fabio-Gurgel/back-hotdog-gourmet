package com.example.backhotdoggourmet.promocao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/promocoes")
@CrossOrigin
public class PromocaoController {

    @Autowired
    PromocaoService promocaoService;

    @GetMapping
    public List<Promocao> getAllPromocoes() {
        return promocaoService.getAllPromocoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocao> getPromocaoById(@PathVariable Long id) {
        Promocao promocaoEncontrada = promocaoService.getPromocaoById(id);
        return ResponseEntity.ok(promocaoEncontrada);
    }

    @PostMapping
    public ResponseEntity<Promocao> createPromocao(@RequestBody Promocao promocao) {
        Promocao promocaoCriada = promocaoService.createPromocao(promocao);
        return ResponseEntity.status(HttpStatus.CREATED).body(promocaoCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocao> updatePromocao(@PathVariable Long id, @RequestBody Promocao promocao) {
        Promocao promocaoEditada = promocaoService.updatePromocao(id, promocao);
        return ResponseEntity.ok(promocaoEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromocao(@PathVariable Long id) {
        promocaoService.deletePromocao(id);
        return ResponseEntity.noContent().build();
    }
}
