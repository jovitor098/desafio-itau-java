package com.example.desafio_itau.controller;

import com.example.desafio_itau.entidade.Transacao;
import com.example.desafio_itau.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> criarTransacao(@RequestBody Transacao transacao){
        transacaoService.addTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
