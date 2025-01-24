package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Estatistica;
import com.example.desafio_itau.entidade.Transacao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EstatisticaServiceTest {
    @Mock
    private TransacaoService transacaoService;

    @InjectMocks
    private EstatisticaService estatisticaService;

    @Test
    void testaEstatisticasCorretas(){
        Transacao transacao1 = new Transacao(5.0, OffsetDateTime.now());
        Transacao transacao2 = new Transacao(10.0, OffsetDateTime.now());
        Transacao transacao3 = new Transacao(15.0, OffsetDateTime.now());

        List<Transacao> transacoes = Arrays.asList(transacao1, transacao2, transacao3);

        when(transacaoService.getTransacoes()).thenReturn(transacoes);

        Estatistica estatistica = estatisticaService.obterEstatisticas(60);
        Estatistica estatisticaCorreta = new Estatistica(3L, 30.0, 10.0, 5.0, 15.0);
        assertEquals(estatisticaCorreta, estatistica);
    }

    @Test
    void testaComListaTransacoesVazia(){
        when(transacaoService.getTransacoes()).thenReturn(new ArrayList<>());

        Estatistica estatistica = estatisticaService.obterEstatisticas(60);
        Estatistica estatisticaCorreta = new Estatistica(0L, 0.0, 0.0, 0.0, 0.0);
        assertEquals(estatisticaCorreta, estatistica);
    }

    @Test
    void testaComUmaTransacaoForaDoTempo(){
        Transacao transacao1 = new Transacao(5.0, OffsetDateTime.now());
        Transacao transacao2 = new Transacao(10.0, OffsetDateTime.now().minusSeconds(120));
        Transacao transacao3 = new Transacao(15.0, OffsetDateTime.now());

        List<Transacao> transacoes = Arrays.asList(transacao1, transacao2, transacao3);
        when(transacaoService.getTransacoes()).thenReturn(transacoes);

        Estatistica estatistica = estatisticaService.obterEstatisticas(60);
        Estatistica estatisticaCorreta = new Estatistica(2L, 20.0, 10.0, 5.0, 15.0);
        assertEquals(estatisticaCorreta, estatistica);
    }
}