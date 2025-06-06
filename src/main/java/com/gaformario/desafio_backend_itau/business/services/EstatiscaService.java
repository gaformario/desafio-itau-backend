package com.gaformario.desafio_backend_itau.business.services;

import com.gaformario.desafio_backend_itau.controller.dto.EstatisticasResponseDTO;
import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatiscaService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO estatiscasTransacoes(Integer intervalo) {
        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoes(intervalo);

        DoubleSummaryStatistics stats = transacoes.stream()
                        .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        return new EstatisticasResponseDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax());
    }
}
