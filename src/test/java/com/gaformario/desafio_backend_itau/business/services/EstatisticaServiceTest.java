package com.gaformario.desafio_backend_itau.business.services;

import com.gaformario.desafio_backend_itau.controller.dto.EstatisticasResponseDTO;
import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstatiscaServiceTest {

    private TransacaoService transacaoService;
    private EstatiscaService estatiscaService;

    @BeforeEach
    void setUp() {
        transacaoService = mock(TransacaoService.class);
        estatiscaService = new EstatiscaService(transacaoService);
    }

    @Test
    void deveRetornarEstatisticasCorretasQuandoExistemTransacoes() {
        List<TransacaoRequestDTO> transacoes = Arrays.asList(
                new TransacaoRequestDTO(10.0, OffsetDateTime.now().minusSeconds(10)),
                new TransacaoRequestDTO(20.0, OffsetDateTime.now().minusSeconds(20)),
                new TransacaoRequestDTO(30.0, OffsetDateTime.now().minusSeconds(30))
        );
        when(transacaoService.buscaTransacoes(60)).thenReturn(transacoes);

        EstatisticasResponseDTO stats = estatiscaService.estatiscasTransacoes(60);

        assertEquals(3L, stats.count());
        assertEquals(60.0, stats.sum());
        assertEquals(20.0, stats.avg());
        assertEquals(10.0, stats.min());
        assertEquals(30.0, stats.max());
    }

    @Test
    void deveRetornarZerosQuandoNaoExistemTransacoes() {
        when(transacaoService.buscaTransacoes(60)).thenReturn(Collections.emptyList());

        EstatisticasResponseDTO stats = estatiscaService.estatiscasTransacoes(60);

        assertEquals(0L, stats.count());
        assertEquals(0.0, stats.sum());
        assertEquals(0.0, stats.avg());
        assertEquals(0.0, stats.min());
        assertEquals(0.0, stats.max());
    }
}