package com.gaformario.desafio_backend_itau.controller.dto;

import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(

        @NotNull
        Double valor,

        @NotNull
        OffsetDateTime dataHora
) {}
