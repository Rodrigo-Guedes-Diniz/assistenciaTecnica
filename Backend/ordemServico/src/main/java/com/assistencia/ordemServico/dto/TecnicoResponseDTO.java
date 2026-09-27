package com.assistencia.ordemServico.dto;

public record TecnicoResponseDTO(
        Long id,
        String nome,
        String especialidade,
        String email
) {
}
