package com.assistencia.ordemServico.controller;

import com.assistencia.ordemServico.dto.TecnicoRequestDTO;
import com.assistencia.ordemServico.dto.TecnicoResponseDTO;
import com.assistencia.ordemServico.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {
    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    //Criar tecnico
    @PostMapping
    public TecnicoResponseDTO criarTecnico(@RequestBody TecnicoRequestDTO dto) {
        return tecnicoService.criarTecnico(dto);
    }


    //Listar todos os tecnicos
    @GetMapping
    public List<TecnicoResponseDTO> listarTodosTecnicos() {
        return tecnicoService.listarTodosTecnicos();
    }

    //Buscar tecnico por id
    @GetMapping("/{id}")
    public TecnicoResponseDTO buscarTecnicoPorId(@PathVariable Long id) {
        return tecnicoService.buscarTecnicoPorId(id);
    }

    //Editar tecnico
    @PutMapping("/{id}")
    public ResponseEntity<TecnicoResponseDTO> editarTecnico(@PathVariable Long id, @RequestBody TecnicoRequestDTO dto) {
        TecnicoResponseDTO responseDTO = tecnicoService.editarTecnico(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    //Deletar tecnico
    @DeleteMapping("/{id}")
    public String deletarTecnico(@PathVariable Long id) {
        return tecnicoService.deletarTecnico(id);
    }



}
