package com.gaformario.desafio_backend_itau.controller;

import com.gaformario.desafio_backend_itau.business.services.TransacaoService;
import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;


    @PostMapping
    @Operation(summary = "Recebe uma nova transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação gravada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição (JSON inválido)"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(
            @Parameter(description = "Dados da transação a ser adicionada", required = true)
            @Valid @RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionaTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(summary = "Limpa e deleta todas as transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limpaTransacoes();
        return ResponseEntity.ok().build();
    }
}
