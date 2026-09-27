package com.assistencia.ordemServico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TecnicoRequestDTO(

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 80, message = "o nome deve conter até 80 caracteres")
        String nome,

        @NotBlank(message = "a especialidade é obrigatória")
        String especialidade,

        @NotBlank(message = "o email é obrigatório")
        String email
) {
}
