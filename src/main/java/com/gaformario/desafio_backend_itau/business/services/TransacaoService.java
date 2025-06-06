package com.gaformario.desafio_backend_itau.business.services;

import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import com.gaformario.desafio_backend_itau.infrastrucutre.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionaTransacao(TransacaoRequestDTO dto) {
        if(dto.dataHora().isAfter(OffsetDateTime.now()) || dto.valor() < 0) {
            throw new UnprocessableEntity("Campos inválidos na criação da transação");
        }

        listaTransacoes.add(dto);
    }

    public void limpaTransacoes() {
        listaTransacoes.clear();
    }
}
