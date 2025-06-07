package com.gaformario.desafio_backend_itau.business.services;

import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import com.gaformario.desafio_backend_itau.infrastrucutre.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransacaoServiceTest {

    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
        transacaoService = new TransacaoService();
    }

    @Test
    void deveAdicionarTransacaoValida() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO(100.0, OffsetDateTime.now().minusMinutes(1));
        assertDoesNotThrow(() -> transacaoService.adicionaTransacao(dto));
    }


    @Test
    void deveLancarExcecaoQuandoDataForFutura() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO(100.0, OffsetDateTime.now().plusMinutes(1));
        assertThrows(UnprocessableEntity.class, () -> transacaoService.adicionaTransacao(dto));
    }

    @Test
    void deveLancarExcecaoQuandoValorForNegativo() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO(-10.0, OffsetDateTime.now());
        assertThrows(UnprocessableEntity.class, () -> transacaoService.adicionaTransacao(dto));
    }

    @Test
    void deveLimparListaDeTransacoes() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO(100.0, OffsetDateTime.now().minusMinutes(1));
        transacaoService.adicionaTransacao(dto);
        transacaoService.limpaTransacoes();
        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoes(60);
        assertTrue(transacoes.isEmpty());
    }
}