package com.example.desafio_itau.service;

import com.example.desafio_itau.entidade.Transacao;
import com.example.desafio_itau.service.exceptions.UnprocessableEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    private final List<Transacao> transacoes = new ArrayList<>();

    public void addTransacao(Transacao transacao){
        if (transacao.valor() ==  null)
            throw new UnprocessableEntity("O valor precisa ser preechido");

        if (transacao.dataHora() ==  null)
            throw new UnprocessableEntity("A data precisa ser preechido");

        if (transacao.valor() < 0)
            throw new UnprocessableEntity("O valor precisa ser positivo");

        if(transacao.dataHora().isAfter(OffsetDateTime.now()))
            throw new UnprocessableEntity("Data da transação precisa ser no passado");

        transacoes.add(transacao);
    }

    public void apagarTransacoes(){
        transacoes.clear();
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
