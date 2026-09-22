package com.assistencia.ordemServico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank(message = "O nome do usuário é obrigatório")
        @Size(max = 80, message = "o nome deve conter até 80 caracteres")
        String nome,

        @NotBlank(message = "o CPF do usuário é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve ter exatamente 11 dígitos")
        String CPF,

        @NotBlank(message = "o telefone do usuário é obrigatório")
        String telefone,

        @NotBlank(message = "o email do usuário é obrigatório")
        String email
) {}
