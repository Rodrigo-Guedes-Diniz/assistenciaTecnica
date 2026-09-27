package com.assistencia.ordemServico.dto;


import jakarta.validation.constraints.NotBlank;

public record OrdemServicoRequestDTO(

        @NotBlank(message = "a descrição do servico é obrigatória")
        String descicao,

        @NotBlank(message = "Selecione o tipo de prioridade")
        Boolean prioridade,

        @NotBlank(message = "A ordem de serviço deve ser vinculada a um equipamento")
        Long equipamentoId,


        Long tecnicoId
) {}
