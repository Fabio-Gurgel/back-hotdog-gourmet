package com.example.backhotdoggourmet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.backhotdoggourmet.ingrediente.Ingrediente;
import com.example.backhotdoggourmet.ingrediente.IngredienteService;
import com.example.backhotdoggourmet.lanche.Lanche;
import com.example.backhotdoggourmet.lanche.LancheRepository;
import com.example.backhotdoggourmet.lanche.LancheService;

public class LancheServiceTest {

    private static final Long ID = new Random().nextLong();
    
    @InjectMocks
    private LancheService lancheService;

    @Mock
    private LancheRepository lancheRepository;

    @Mock
    private IngredienteService ingredienteService;

    private Lanche lanche;
    private Ingrediente ingrediente;
    List<Ingrediente> ingredientes = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        initObject();
    }

    @Test
    void deveVerificarPrecoDoLancheDoCardapio() {
        lanche = new Lanche(ID, "Completo", 2.5, ingredientes);
        when(lancheRepository.findById(ID)).thenReturn(Optional.of(lanche));

        Lanche response =  lancheService.getLancheById(ID);

        assertNotNull(response);
        assertInstanceOf(Lanche.class, response);
        assertEquals(2.5, response.getPreco());
        
    }

    @Test
    void deveDefinirOValorDoLancheAoReceberPrecoNull() {
        lanche = new Lanche(ID, "Completo", null, ingredientes);
        when(lancheRepository.save(lanche)).thenReturn(lanche);
        when(ingredienteService.getIngredienteById(Mockito.anyLong())).thenReturn(ingrediente);

        Lanche response =  lancheService.createLanche(lanche);

        assertNotNull(response);
        assertInstanceOf(Lanche.class, response);
        assertEquals(2.5, response.getPreco());
        
    }

    @Test
    void deveDefinirOValorDoLancheCorretamenteMesmoRecebendoUmValorJaDefinido() {
        lanche = new Lanche(ID, "Completo", 10.0, ingredientes);

        when(lancheRepository.save(lanche)).thenReturn(lanche);
        when(ingredienteService.getIngredienteById(Mockito.anyLong())).thenReturn(ingrediente);

        Lanche response =  lancheService.createLanche(lanche);

        assertNotNull(response);
        assertInstanceOf(Lanche.class, response);
        assertEquals(2.5, response.getPreco());
    }

    @Test
    void deveDefinirOValorDoLancheAoReceberPrecoNullDuranteEdicao() {
        lanche = new Lanche(ID, "Completo", null, ingredientes);
    
        when(lancheRepository.findById(ID)).thenReturn(Optional.of(lanche));
        when(lancheRepository.save(lanche)).thenReturn(lanche);
        when(ingredienteService.getIngredienteById(Mockito.anyLong())).thenReturn(ingrediente);

        Lanche response =  lancheService.updateLanche(ID, lanche);

        assertNotNull(response);
        assertInstanceOf(Lanche.class, response);
        assertEquals(2.5, response.getPreco());
    }

    @Test
    void deveDefinirOValorDoLancheCorretamenteMesmoRecebendoUmValorJaDefinidoDuranteEdicao() {
        lanche = new Lanche(ID, "Completo", 10.0, ingredientes);
    
        when(lancheRepository.findById(ID)).thenReturn(Optional.of(lanche));
        when(lancheRepository.save(lanche)).thenReturn(lanche);
        when(ingredienteService.getIngredienteById(Mockito.anyLong())).thenReturn(ingrediente);

        Lanche response =  lancheService.updateLanche(ID, lanche);

        assertNotNull(response);
        assertInstanceOf(Lanche.class, response);
        assertEquals(2.5, response.getPreco());
    }

    @Test
    void deveVerificarSeOLancheRecebeOsIngredientesCorretamente() {
        lanche = new Lanche(ID, "Completo", 10.0, ingredientes);

        when(lancheRepository.save(lanche)).thenReturn(lanche);
        when(ingredienteService.getIngredienteById(Mockito.anyLong())).thenReturn(ingrediente);

        Lanche response =  lancheService.createLanche(lanche);

        assertNotNull(response);
        assertInstanceOf(Lanche.class, response);

        assertInstanceOf(Ingrediente.class, response.getIngredientes().get(0));
        assertEquals("Salsicha", response.getIngredientes().get(0).getNome());
        assertEquals(2.5, response.getIngredientes().get(0).getPreco());
    }

    public void initObject() {
        ingrediente = new Ingrediente(ID, "Salsicha", 2.5);
        ingredientes.add(ingrediente);
    }
}
