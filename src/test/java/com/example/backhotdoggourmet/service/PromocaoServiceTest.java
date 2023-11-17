package com.example.backhotdoggourmet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.backhotdoggourmet.ingrediente.Ingrediente;
import com.example.backhotdoggourmet.lanche.Lanche;
import com.example.backhotdoggourmet.lanche.LancheService;
import com.example.backhotdoggourmet.promocao.Promocao;
import com.example.backhotdoggourmet.promocao.PromocaoRepository;
import com.example.backhotdoggourmet.promocao.PromocaoService;

public class PromocaoServiceTest {

    
    private static final Long ID = new Random().nextLong();

    
    @InjectMocks
    PromocaoService promocaoService;

    @Mock
    PromocaoRepository promocaoRepository;

    @Mock
    LancheService lancheService;

    Promocao promocao;
    Lanche lanche;
    Ingrediente ingrediente;
    List<Ingrediente> ingredientes = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        initObject();
    }

    @Test
    void oPrecoDaPromocaoDeveSerDe10ReaisPorJaEstarDefinidoENaoTerDesconto() {
        promocao = new Promocao(ID, "Completão + Refri 300ml", "Lanche completo com refrigerante", 10.0, null, List.of(lanche));

        when(promocaoRepository.save(promocao)).thenReturn(promocao);
        when(lancheService.getLancheById(Mockito.anyLong())).thenReturn(lanche);

        Promocao response = promocaoService.createPromocao(promocao);

        assertNotNull(response);
        assertInstanceOf(Promocao.class, response);
        assertEquals(10.0, response.getPreco());
    }

    @Test
    void oPrecoDaPromocaoDeveSerDe5ReaisPorJaEstarDefinidoETerDescontoDe50Porcento() {
        promocao = new Promocao(ID, "Completão + Refri com 50% OFF", "Lanche completo + refrigerante com 50% de desconto", 10.0, 50.0, List.of(lanche));

        when(promocaoRepository.save(promocao)).thenReturn(promocao);
        when(lancheService.getLancheById(Mockito.anyLong())).thenReturn(lanche);

        Promocao response = promocaoService.createPromocao(promocao);

        assertNotNull(response);
        assertInstanceOf(Promocao.class, response);
        assertEquals(5.0, response.getPreco());
    }

    @Test
    void oPrecoDaPromocaoDeveSerOMesmoPrecoDoLanchePorNaoEstarDefinido() {
        promocao = new Promocao(ID, "Completão", "Lanche completo normal", null, null, List.of(lanche));

        when(promocaoRepository.save(promocao)).thenReturn(promocao);
        when(lancheService.getLancheById(Mockito.anyLong())).thenReturn(lanche);

        Promocao response = promocaoService.createPromocao(promocao);

        assertNotNull(response);
        assertInstanceOf(Promocao.class, response);
        assertEquals(2.5, response.getPreco());
    }

    @Test
    void oPrecoDaPromocaoDeveSerOMesmoPrecoDoLanchePoremComDescontoDe20Porcento() {
        promocao = new Promocao(ID, "Completão com 20% OFF", "Lanche completo normal com 20% de desconto", null, 20.0, List.of(lanche));

        when(promocaoRepository.save(promocao)).thenReturn(promocao);
        when(lancheService.getLancheById(Mockito.anyLong())).thenReturn(lanche);

        Promocao response = promocaoService.createPromocao(promocao);

        assertNotNull(response);
        assertInstanceOf(Promocao.class, response);
        assertEquals(2.0, response.getPreco());
    }

    public void initObject() {
        ingrediente = new Ingrediente(ID, "Salsicha", 2.5);
        ingredientes.add(ingrediente);
        lanche = new Lanche(ID, "Completo", 2.5, ingredientes);
    }
}
