package com.example.desafio_itau.entidade;

public record Estatistica (
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}
