package com.assistencia.ordemServico.controller;

import com.assistencia.ordemServico.dto.ClienteRequestDTO;
import com.assistencia.ordemServico.dto.ClienteResponseDTO;
import com.assistencia.ordemServico.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController (ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Criar cliente
    @PostMapping
    public ClienteResponseDTO cadastrarCLiente(@RequestBody ClienteRequestDTO dto) {
        return clienteService.createCliente(dto);
    }

    //Listar todos os clientes
    @GetMapping
    public List<ClienteResponseDTO> listarTodosClientes() {
        return clienteService.listarTodosClientes();
    }

    //Buscar cliente por Id
    @GetMapping("/{id}")
    public ClienteResponseDTO buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }

    //Editar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id,@RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO responseDTO = clienteService.editarCliente(id, dto);

        return ResponseEntity.ok(responseDTO);
    }

    //Deletar Cliente
    @DeleteMapping("{id}")
    public String deletarCliente(@PathVariable Long id) {
        clienteService.deletarCLiente(id);
        return "cliente deletado com sucesso!";
    }

}
