package com.gaformario.desafio_backend_itau.business.services;

import com.gaformario.desafio_backend_itau.controller.dto.EstatisticasResponseDTO;
import com.gaformario.desafio_backend_itau.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstatiscaService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO estatiscasTransacoes(Integer intervalo) {
        log.info("Buscando estatísticas de transações para os últimos {} segundos", intervalo);

        long inicio = System.currentTimeMillis();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscaTransacoes(intervalo);

        if (transacoes.isEmpty()) {
            log.warn("Nenhuma transação encontrada no intervalo especificado.");
            long fim = System.currentTimeMillis();
            log.info("Tempo gasto para calcular estatísticas: {} ms", (fim - inicio));
            return new EstatisticasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics stats = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long fim = System.currentTimeMillis();

        log.info("Estatísticas calculadas: count={}, sum={}, avg={}, min={}, max={}",
                stats.getCount(), stats.getSum(), stats.getAverage(), stats.getMin(), stats.getMax());

        log.info("Tempo gasto para calcular estatísticas: {} ms", (fim - inicio));
        log.info("Estatisticas retornadas com sucesso");
        return new EstatisticasResponseDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax());
    }
}
