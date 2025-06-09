package com.gaformario.desafio_backend_itau.business.services;

import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import com.gaformario.desafio_backend_itau.infrastructure.entities.Transacao;
import com.gaformario.desafio_backend_itau.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final List<Transacao> listaTransacoes = new ArrayList<>();

    public void adicionaTransacao(TransacaoRequestDTO dto) {
        log.info("Processo para adicionar nova transação: {}", dto);

        if(dto.dataHora().isAfter(OffsetDateTime.now()) || dto.valor() < 0) {
            log.error("Transação inválida: dataHora={}, valor={}", dto.dataHora(), dto.valor());
            throw new UnprocessableEntity("Campos inválidos na criação da transação");
        }

        Transacao novaTransacao = new Transacao(dto.valor(), dto.dataHora());
        listaTransacoes.add(novaTransacao);
        log.info("Transação adicionada com sucesso");
    }

    public void limpaTransacoes() {
        log.info("Iniciando processo de limpeza das transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");
    }

    public List<Transacao> buscaTransacoes(Integer intervalo) {
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervalo);

        log.info("Transações retornadas com sucesso");
        return listaTransacoes.stream()
                .filter(transacao -> transacao.getDataHora().isAfter(dataHoraIntervalo))
                .toList();
    }
}
