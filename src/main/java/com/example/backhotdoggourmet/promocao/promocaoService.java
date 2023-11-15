package com.example.backhotdoggourmet.promocao;

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

        if(promocaoEncontrada.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar a promoção com id " + id + ".");
        }

        return promocaoEncontrada.get();
    }

    public Promocao createPromocao(Promocao promocao) {

        if(promocao.getLanche() == null) {
            throw new ResourceBadRequestException("Não é possível criar uma promoção sem informar o lanche.");
        }

        if(promocaoRepository.findByNome(promocao.getNome()) != null) {
            throw new ResourceBadRequestException("Já existe uma promoção com esse mesmo nome.");
        }

        Lanche lancheEncontrado = lancheService.getLancheById(promocao.getLanche().getId());
        promocao.setLanche(lancheEncontrado);

        return promocaoRepository.save(promocao);
    }

    public Promocao updatePromocao(Long id, Promocao promocao) {
        if(promocao.getLanche() == null) {
            throw new ResourceBadRequestException("Não é possível criar uma promoção sem informar o lanche.");
        }

        Promocao promocaoExistente = getPromocaoById(id);

        if (!promocaoExistente.getNome().equals(promocao.getNome())) {
            Promocao promocaoComMesmoNome = promocaoRepository.findByNome(promocao.getNome());
            if (promocaoComMesmoNome != null) {
                throw new ResourceBadRequestException("Já existe uma promoção com esse mesmo nome.");
            }
        }

        Lanche lancheEncontrado = lancheService.getLancheById(promocao.getLanche().getId());

        promocaoExistente.setNome(promocao.getNome());
        promocaoExistente.setDescricao(promocao.getDescricao());
        promocaoExistente.setPreco(promocao.getPreco());
        promocaoExistente.setLanche(lancheEncontrado);

        return promocaoRepository.save(promocaoExistente);
    }

    public void deletePromocao(Long id) {
        getPromocaoById(id);
        promocaoRepository.deleteById(id);
    }
    
}
