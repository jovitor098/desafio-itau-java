package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Transacao;
import com.example.desafio_itau.service.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransacaoServiceTest {
    @Autowired
    private TransacaoService transacaoService;

    @BeforeEach
    void setup(){
        transacaoService.getTransacoes().clear();
    }

    @Test
    void pegarListaTransacoesVazia() {
        List<Transacao> transacoes = transacaoService.getTransacoes();
        assertEquals(0, transacoes.size());
    }

    @Test
    void adicionarTransacaoLista(){
        Transacao transacao = new Transacao(10.0, OffsetDateTime.now());
        transacaoService.addTransacao(transacao);
        assertEquals(1, transacaoService.getTransacoes().size());
    }

    @Test
    void verificarExcecaoDeValorNegativo(){
        Transacao transacao = new Transacao(-10.0, OffsetDateTime.now());
        assertThrows(UnprocessableEntity.class, () -> transacaoService.addTransacao(transacao));
    }
    @Test
    void verificarExcecaoDataNoFuturo(){
        Transacao transacao = new Transacao(10.0, OffsetDateTime.now().plusHours(1));
        assertThrows(UnprocessableEntity.class, () -> transacaoService.addTransacao(transacao));
    }

    @Test
    void verificarExcecaoValoresNull(){
        Transacao transacao = new Transacao(null, OffsetDateTime.now());
        Transacao finalTransacao = transacao;
        assertThrows(UnprocessableEntity.class, () -> transacaoService.addTransacao(finalTransacao));

        transacao = new Transacao(10.0, null);
        Transacao finalTransacao1 = transacao;
        assertThrows(UnprocessableEntity.class, () -> transacaoService.addTransacao(finalTransacao1));
    }

    @Test
    void apagarTodasAsTransacoes(){
        Transacao transacao = new Transacao(10.0, OffsetDateTime.now());
        transacaoService.addTransacao(transacao);
        transacaoService.apagarTransacoes();
        assertEquals(0, transacaoService.getTransacoes().size());
    }
}