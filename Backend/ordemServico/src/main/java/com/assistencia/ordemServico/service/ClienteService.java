package com.assistencia.ordemServico.service;

import com.assistencia.ordemServico.dto.ClienteRequestDTO;
import com.assistencia.ordemServico.dto.ClienteResponseDTO;
import com.assistencia.ordemServico.entity.Cliente;
import com.assistencia.ordemServico.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Criar cliente
    public ClienteResponseDTO createCliente(ClienteRequestDTO dto) {
        Cliente novoCliente = new Cliente();

        novoCliente.setNome(dto.nome());
        novoCliente.setCPF(dto.CPF());
        novoCliente.setTelefone(dto.telefone());
        novoCliente.setEmail(dto.email());

        Cliente cliente = clienteRepository.save(novoCliente);
        return convertToResponseDTO(cliente);
    }

    //Listar todos os clientes
    public List<ClienteResponseDTO>  listarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    //Buscar cliente por ID
    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente Não encontrado com o Id " + id));

        return convertToResponseDTO(cliente);
    }

    //Editar cliente
    public ClienteResponseDTO editarCliente(Long id, ClienteRequestDTO dto) {
        Cliente novoCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente Não encontrado com o Id " + id));

        novoCliente.setNome(dto.nome());
        novoCliente.setCPF(dto.CPF());
        novoCliente.setTelefone(dto.telefone());
        novoCliente.setTelefone(dto.telefone());
        novoCliente.setEmail(dto.email());

        Cliente clienteUpdated = clienteRepository.save(novoCliente);

        return convertToResponseDTO(clienteUpdated);
    }


    //Deletar cliente
    public String deletarCLiente(Long id) {
        Cliente novoCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado com o ID " + id));

        clienteRepository.delete(novoCliente);
        return "Cliente removido com sucesso";
    }


    //Converter para cliente para ClienteResponseDTO
    private ClienteResponseDTO convertToResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCPF(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }

}
