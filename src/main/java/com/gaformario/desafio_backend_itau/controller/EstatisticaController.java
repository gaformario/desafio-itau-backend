package com.gaformario.desafio_backend_itau.controller;

import com.gaformario.desafio_backend_itau.business.services.EstatisticaService;
import com.gaformario.desafio_backend_itau.controller.dto.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(summary = "Obtém as estatísticas de transações durante um intervalo de tempo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transações"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> obterEstatisticas(@RequestParam(value = "intervalo", required = false, defaultValue = "60") Integer intervalo) {
        return ResponseEntity.ok(estatisticaService.estatiscasTransacoes(intervalo));
    }
}
