package com.example.backhotdoggourmet.promocao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backhotdoggourmet.exceptions.ResourceBadRequestException;
import com.example.backhotdoggourmet.exceptions.ResourceNotFoundException;
import com.example.backhotdoggourmet.lanche.Lanche;
import com.example.backhotdoggourmet.lanche.LancheService;

@Service
public class PromocaoService {

    @Autowired
    PromocaoRepository promocaoRepository;

    @Autowired
    LancheService lancheService;

    public List<Promocao> getAllPromocoes() {
        return promocaoRepository.findAll();
    }

    public Promocao getPromocaoById(Long id) {
        Optional<Promocao> promocaoEncontrada = promocaoRepository.findById(id);

        if (promocaoEncontrada.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar a promoção com id " + id + ".");
        }

        return promocaoEncontrada.get();
    }

    public Promocao createPromocao(Promocao promocao) {
        Double precoDaPromocao = 0.0;

        List<Lanche> lanchesDaPromocao = new ArrayList<>();

        if (promocao.getLanches().isEmpty()) {
            throw new ResourceBadRequestException("Não é possível criar uma promoção sem informar o lanche.");
        }

        for(Lanche lanche : promocao.getLanches()) {
            lanchesDaPromocao.add(lancheService.getLancheById(lanche.getId()));
        }

        promocao.setLanches(lanchesDaPromocao);

        if (promocao.getPreco() == null) {
            for (Lanche lanche : promocao.getLanches()) {
                precoDaPromocao += lanche.getPreco();
            }
        
            if (promocao.getPercentualDesconto() != null) {
                Double descontoEmReais = precoDaPromocao * (promocao.getPercentualDesconto() / 100.0);
        
                precoDaPromocao -= descontoEmReais;
            }
        } else {
            precoDaPromocao = promocao.getPreco();

            if (promocao.getPercentualDesconto() != null) {
                Double descontoEmReais = precoDaPromocao * (promocao.getPercentualDesconto() / 100.0);
        
                precoDaPromocao -= descontoEmReais;
            }
        }
        
        promocao.setPreco(precoDaPromocao);
        

        if (promocao.getPreco() < 0) {
            throw new ResourceBadRequestException("Não é possível criar uma promoção com preço negativo.");
        }
        
        if (promocaoRepository.findByNome(promocao.getNome()) != null) {
            throw new ResourceBadRequestException("Já existe uma promoção com esse mesmo nome.");
        }

        return promocaoRepository.save(promocao);
    }

    public Promocao updatePromocao(Long id, Promocao promocao) {
        Double precoDaPromocao = 0.0;
        List<Lanche> lanchesDaPromocao = new ArrayList<>();

        if (promocao.getLanches().isEmpty()) {
            throw new ResourceBadRequestException("Não é possível criar uma promoção sem informar o lanche.");
        }

        for(Lanche lanche : promocao.getLanches()) {
            lanchesDaPromocao.add(lancheService.getLancheById(lanche.getId()));
        }

        promocao.setLanches(lanchesDaPromocao);

        if (promocao.getPreco() == null) {
            for (Lanche lanche : promocao.getLanches()) {
                precoDaPromocao += lanche.getPreco();
            }
        
            if (promocao.getPercentualDesconto() != null) {
                Double descontoEmReais = precoDaPromocao * (promocao.getPercentualDesconto() / 100.0);
        
                precoDaPromocao -= descontoEmReais;
            }
        } else {
            precoDaPromocao = promocao.getPreco();

            if (promocao.getPercentualDesconto() != null) {
                Double descontoEmReais = precoDaPromocao * (promocao.getPercentualDesconto() / 100.0);
        
                precoDaPromocao -= descontoEmReais;
            }
        }
        
        promocao.setPreco(precoDaPromocao);


        if (promocao.getPreco() < 0) {
            throw new ResourceBadRequestException("Não é possível criar uma promoção com preço negativo.");
        }

        Promocao promocaoExistente = getPromocaoById(id);

        if (!promocaoExistente.getNome().equals(promocao.getNome())) {
            Promocao promocaoComMesmoNome = promocaoRepository.findByNome(promocao.getNome());
            if (promocaoComMesmoNome != null) {
                throw new ResourceBadRequestException("Já existe uma promoção com esse mesmo nome.");
            }
        }

        promocaoExistente.setNome(promocao.getNome());
        promocaoExistente.setDescricao(promocao.getDescricao());
        promocaoExistente.setPreco(promocao.getPreco());
        promocaoExistente.setPercentualDesconto(promocao.getPercentualDesconto());
        promocaoExistente.setLanches(promocao.getLanches());

        return promocaoRepository.save(promocaoExistente);
    }

    public void deletePromocao(Long id) {
        getPromocaoById(id);
        promocaoRepository.deleteById(id);
    }

}
