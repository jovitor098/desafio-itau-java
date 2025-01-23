package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Transacao;
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
}