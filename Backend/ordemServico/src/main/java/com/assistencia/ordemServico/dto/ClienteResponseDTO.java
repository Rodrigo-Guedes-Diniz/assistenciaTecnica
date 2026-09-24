package com.assistencia.ordemServico.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String CPF,
        String telefone,
        String email
) { }
