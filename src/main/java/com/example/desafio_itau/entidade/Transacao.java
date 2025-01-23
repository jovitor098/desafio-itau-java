package com.example.desafio_itau.entidade;

import java.time.OffsetDateTime;

public record Transacao(
        Double valor,
        OffsetDateTime dataHora
) {
}
