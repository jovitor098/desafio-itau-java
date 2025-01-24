package com.example.desafio_itau.controller;

import com.example.desafio_itau.entidade.Estatistica;
import com.example.desafio_itau.service.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {
    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity<Estatistica> obterEstatistica(@RequestParam(
            required = false,
            defaultValue = "60",
            name = "tempo") Integer tempo) {
        Estatistica estatistica = estatisticaService.obterEstatisticas(tempo);
        return ResponseEntity.ok(estatistica);
    }

}
