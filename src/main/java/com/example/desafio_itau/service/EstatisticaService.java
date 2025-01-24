package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Estatistica;
import com.example.desafio_itau.entidade.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticaService {
    @Autowired
    private TransacaoService transacaoService;

    public Estatistica obterEstatisticas(Integer tempo) {
        OffsetDateTime horarioMaximo = OffsetDateTime.now().minusSeconds(tempo);
        List<Transacao> todasTransacoes = transacaoService.getTransacoes();
        List<Transacao> transacoesNoUltimoTempo = todasTransacoes.stream()
                                                                 .filter(transacao -> transacao.dataHora().isAfter(horarioMaximo))
                                                                 .toList();

        if (transacoesNoUltimoTempo.isEmpty())
            return new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);

        DoubleSummaryStatistics estatisticas = transacoesNoUltimoTempo.stream()
                                                                      .mapToDouble(Transacao::valor)
                                                                      .summaryStatistics();

        return new Estatistica(estatisticas.getCount(), estatisticas.getSum(), estatisticas.getAverage(),
                estatisticas.getMin(), estatisticas.getMax());
    }
}
