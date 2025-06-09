package com.gaformario.desafio_backend_itau.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class Transacao {

    private double valor;
    private OffsetDateTime dataHora;

}
