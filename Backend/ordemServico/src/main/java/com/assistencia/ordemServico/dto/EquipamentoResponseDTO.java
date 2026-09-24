package com.assistencia.ordemServico.dto;

public record EquipamentoResponseDTO(
        Long id,
        String tipo,
        String marca,
        String modelo,
        Long numeroDeSerie,
        ClienteResponseDTO cliente
) {}
