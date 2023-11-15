package com.example.backhotdoggourmet.promocao;

import java.util.List;

import com.example.backhotdoggourmet.lanche.Lanche;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String nome;

    private String descricao;

    private double preco;

    @OneToMany
    @JoinColumn(name = "lanche_id")
    private List<Lanche> lanches;

    public Promocao(long id, String nome, String descricao, double preco, List<Lanche> lanche) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.lanches = lanche;
    }

    public Promocao() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Lanche> getLanches() {
        return lanches;
    }

    public void setLanches(List<Lanche> lanches) {
        this.lanches = lanches;
    }

}