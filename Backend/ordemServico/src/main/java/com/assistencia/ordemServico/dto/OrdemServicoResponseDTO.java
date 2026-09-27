package com.assistencia.ordemServico.dto;

import java.time.LocalDateTime;

public record OrdemServicoResponseDTO(
         Long id,
         String descricao,
         LocalDateTime data,
         String status,
         Boolean prioridade,
         EquipamentoResponseDTO equipamento,
         TecnicoResponseDTO tecnico
) {}