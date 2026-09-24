package com.assistencia.ordemServico.controller;

import com.assistencia.ordemServico.dto.EquipamentoRequestDTO;
import com.assistencia.ordemServico.dto.EquipamentoResponseDTO;
import com.assistencia.ordemServico.service.EquipamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    //Criar equipamento
    @PostMapping
    public EquipamentoResponseDTO criarEquipamento(@RequestBody EquipamentoRequestDTO dto) {
        return equipamentoService.criarEquipamento(dto);
    }

    //Listar todos os equipamentos
    @GetMapping
    public List<EquipamentoResponseDTO> buscarTodosEquipamentos() {
        return equipamentoService.buscarTodosEquipamentos();
    }

    //Buscar equipamento por ID
    @GetMapping("/{id}")
    public EquipamentoResponseDTO buscarEquipamentoPorId(@PathVariable Long id) {
        return equipamentoService.buscarEquipamentoPorId(id);
    }

    //Buscar equipamento por cliente
    @GetMapping("/{clienteId}")
    public List<EquipamentoResponseDTO> buscarEquipamentoPorCliente(@PathVariable Long clienteId) {
        List<EquipamentoResponseDTO> equipamentos = equipamentoService.buscarEquipamentoPorCliente(clienteId);
        return equipamentos;
    }

    //Editar equipamento
    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoResponseDTO> EditarEquipamento(@PathVariable Long id, @RequestBody EquipamentoRequestDTO dto) {
        EquipamentoResponseDTO responseDTO = equipamentoService.editarEquipamento(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    //Deletar equipamento
    @DeleteMapping("/{id}")
    public String deletarEquipamento(@PathVariable Long id) {
        equipamentoService.deletarEquipamento(id);
        return "Equipamento deletado com sucesso!";
    }

}
