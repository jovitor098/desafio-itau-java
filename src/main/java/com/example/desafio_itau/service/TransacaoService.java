package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Transacao;
import com.example.desafio_itau.service.exceptions.UnprocessableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);
    private final List<Transacao> transacoes = new ArrayList<>();

    public void addTransacao(Transacao transacao){
        log.info("Inicio das verificações da requisicao");
        if (transacao.valor() ==  null) {
            log.error("Erro null no valor");
            throw new UnprocessableEntity("O valor precisa ser preechido");
        }

        if (transacao.dataHora() ==  null) {
            log.error("Erro null na data");
            throw new UnprocessableEntity("A data precisa ser preechida");
        }

        if (transacao.valor() < 0) {
            log.error("Erro no valor ser negativo");
            throw new UnprocessableEntity("O valor precisa ser positivo");
        }

        if(transacao.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Erro na data ser no futuro");
            throw new UnprocessableEntity("Data da transação precisa ser no passado");
        }

        log.info("Verificações feitas com sucesso");
        transacoes.add(transacao);
    }

    public void apagarTransacoes(){
        log.info("Limpando as transações");
        transacoes.clear();
    }

    public List<Transacao> getTransacoes() {
        log.info("Retornando as transações");
        return transacoes;
    }
}
