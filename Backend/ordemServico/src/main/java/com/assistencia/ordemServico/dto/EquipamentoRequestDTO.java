package com.assistencia.ordemServico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EquipamentoRequestDTO(

        @NotBlank(message = "O tipo é obrigatório")
        String tipo,

        @NotBlank(message = "A marca é obrigatória")
        String marca,

        @NotBlank(message = "O modelo é obrigatório")
        String modelo,

        @NotBlank(message = "O numero de serie é obrigatório")
        Long numeroDeSerie,

        @NotNull(message = "O ID do cliente vinculado é obrigatório")
        Long clienteId
) {}
