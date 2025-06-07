package com.gaformario.desafio_backend_itau.controller;

import com.gaformario.desafio_backend_itau.business.services.TransacaoService;
import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransacaoControllerTest {

    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    public TransacaoControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarCreatedAoAdicionarTransacao() {
        TransacaoRequestDTO dto = new TransacaoRequestDTO(100.0, OffsetDateTime.now());
        doNothing().when(transacaoService).adicionaTransacao(dto);

        ResponseEntity<Void> response = transacaoController.adicionarTransacao(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(transacaoService, times(1)).adicionaTransacao(dto);
    }

    @Test
    void deveRetornarOkAoDeletarTransacoes() {
        doNothing().when(transacaoService).limpaTransacoes();

        ResponseEntity<Void> response = transacaoController.deletarTransacoes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(transacaoService, times(1)).limpaTransacoes();
    }
}