package com.gaformario.desafio_backend_itau.controller;

import com.gaformario.desafio_backend_itau.business.services.EstatiscaService;
import com.gaformario.desafio_backend_itau.controller.dto.EstatisticasResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EstatisticaControllerTest {

    @Mock
    private EstatiscaService estatiscaService;

    @InjectMocks
    private EstatisticaController estatisticaController;

    public EstatisticaControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarEstatisticasAoObterEstatisticas() {
        EstatisticasResponseDTO dto = new EstatisticasResponseDTO(1L, 100.0, 100.0, 100.0, 100.0);
        when(estatiscaService.estatiscasTransacoes(60)).thenReturn(dto);

        ResponseEntity<EstatisticasResponseDTO> response = estatisticaController.obterEstatisticas(60);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());
        verify(estatiscaService, times(1)).estatiscasTransacoes(60);
    }
}