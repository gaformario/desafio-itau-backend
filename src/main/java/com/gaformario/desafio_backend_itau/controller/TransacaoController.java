package com.gaformario.desafio_backend_itau.controller;

import com.gaformario.desafio_backend_itau.business.services.TransacaoService;
import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;


    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@Validated @RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionaTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
