package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Transacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransacaoServiceTest {
    @Autowired
    private TransacaoService transacaoService;

    @Test
    void pegarListaTransacoesVazia() {
        List<Transacao> transacoes = transacaoService.getTransacoes();
        assertEquals(0, transacoes.size());
    }
}