package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Estatistica;
import com.example.desafio_itau.entidade.Transacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {
    private static final Logger log = LoggerFactory.getLogger(EstatisticaService.class);
    @Autowired
    private TransacaoService transacaoService;

    public Estatistica obterEstatisticas(Integer tempo) {
        log.info("Obtendo o horario maximo");
        OffsetDateTime horarioMaximo = OffsetDateTime.now().minusSeconds(tempo);

        log.info("Obtendo todas as transações");
        List<Transacao> todasTransacoes = transacaoService.getTransacoes();

        log.info("Obtendo as transações no intervalo de tempo");
        List<Transacao> transacoesNoUltimoTempo = todasTransacoes.stream()
                                                                 .filter(transacao -> transacao.dataHora().isAfter(horarioMaximo))
                                                                 .toList();

        log.info("Verificando se a lista é vazia");
        if (transacoesNoUltimoTempo.isEmpty())
            return new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);

        log.info("Obtendo as estatísticas");
        DoubleSummaryStatistics estatisticas = transacoesNoUltimoTempo.stream()
                                                                      .mapToDouble(Transacao::valor)
                                                                      .summaryStatistics();

        return new Estatistica(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(),
                estatisticas.getMin(), estatisticas.getMax());
    }
}
