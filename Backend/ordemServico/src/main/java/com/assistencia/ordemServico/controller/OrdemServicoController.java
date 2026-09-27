package com.assistencia.ordemServico.controller;

import com.assistencia.ordemServico.dto.OrdemServicoRequestDTO;
import com.assistencia.ordemServico.dto.OrdemServicoResponseDTO;
import com.assistencia.ordemServico.service.EquipamentoService;
import com.assistencia.ordemServico.service.OrdemServicoService;
import com.assistencia.ordemServico.service.TecnicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordensServico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService, TecnicoService tecnicoService, EquipamentoService equipamentoService) {
        this.ordemServicoService = ordemServicoService;
    }

    //Cadastrar ordem de servico
    @PostMapping
    public OrdemServicoResponseDTO cadastrarOrdemServico(@RequestBody OrdemServicoRequestDTO dto) {
        return ordemServicoService.cadastrarOrdemServico(dto, dto.equipamentoId(), dto.tecnicoId());
    }

    //Listar todas as ordens de servico
    @GetMapping
    public List<OrdemServicoResponseDTO> listarTodasOrdensServico() {
        return ordemServicoService.listarTodasOrdemsServico();
    }

    //Listar ordem de servico por Id
    @GetMapping("/{id}")
    public OrdemServicoResponseDTO buscarOrdemServicoPorId(@PathVariable Long id) {
        return ordemServicoService.buscarOrdemServicoPorId(id);
    }

    //Editar ordem de servico
    @PutMapping("/{id}")
    public OrdemServicoResponseDTO editarOrdemServico(@PathVariable Long id,@RequestBody OrdemServicoRequestDTO dto) {
        return ordemServicoService.editarOrdemServico(id, dto);
    }

    //Excluir ordem de servico
    @DeleteMapping("/{id}")
    public String excluirOrdemServico(@PathVariable Long id) {
        return ordemServicoService.deletarOrdemServico(id);
    }

}
