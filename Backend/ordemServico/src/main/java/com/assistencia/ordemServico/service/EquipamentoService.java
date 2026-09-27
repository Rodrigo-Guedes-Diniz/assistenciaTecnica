package com.assistencia.ordemServico.service;

import com.assistencia.ordemServico.dto.ClienteResponseDTO;
import com.assistencia.ordemServico.dto.EquipamentoRequestDTO;
import com.assistencia.ordemServico.dto.EquipamentoResponseDTO;
import com.assistencia.ordemServico.entity.Cliente;
import com.assistencia.ordemServico.entity.Equipamento;
import com.assistencia.ordemServico.repository.ClienteRepository;
import com.assistencia.ordemServico.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final ClienteRepository clienteRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, ClienteRepository clienteRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.clienteRepository = clienteRepository;
    }

    //Cadastrar equipamento
    public EquipamentoResponseDTO criarEquipamento(EquipamentoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new NoSuchElementException("Cliente Não encontrado com o Id " + dto.clienteId()));

        Equipamento novoEquipamento = new Equipamento();
        novoEquipamento.setTipo(dto.tipo());
        novoEquipamento.setMarca(dto.marca());
        novoEquipamento.setModelo(dto.modelo());
        novoEquipamento.setNumeroDeSerie(dto.numeroDeSerie());

        novoEquipamento.setCliente(cliente);
        Equipamento equipamento = equipamentoRepository.save(novoEquipamento);

        return convertToResponseDTO(equipamento);
    }


    //Buscar todos os equipamentos
    public List<EquipamentoResponseDTO> buscarTodosEquipamentos() {
        List<Equipamento> equipamento = equipamentoRepository.findAll();

        return equipamento.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    //Buscar equipamento por Id
    public EquipamentoResponseDTO buscarEquipamentoPorId(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipamento nao encontrado com o id " + id));

        return convertToResponseDTO(equipamento);
    }

    //Buscar equipamento por Cliente
    public List<EquipamentoResponseDTO> buscarEquipamentoPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new NoSuchElementException("Cliente nao encontrado com o ID " + clienteId);
        }

        List<Equipamento> equipamentos = equipamentoRepository.findByCliente(clienteId);

        return equipamentos.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    //Editar equipamento
    public EquipamentoResponseDTO editarEquipamento(Long id, EquipamentoRequestDTO dto) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipamento nao encontrado com o Id " + id));

        equipamento.setTipo(dto.tipo());
        equipamento.setMarca(dto.marca());
        equipamento.setModelo(dto.modelo());
        equipamento.setNumeroDeSerie(dto.numeroDeSerie());

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new NoSuchElementException("cliente nao encontrado com id " + dto.clienteId()));
        equipamento.setCliente(cliente);

        Equipamento equipamentoEditado = equipamentoRepository.save(equipamento);
        return convertToResponseDTO(equipamentoEditado);
    }


    //Deletar equipamento
    public String deletarEquipamento(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("equipamento nao encontrado com o id " + id));

        equipamentoRepository.delete(equipamento);

        return "Equipamento deletado com sucesso";
    }

    //Transformando de Equipamento para equipamentoResponseDTO
    private EquipamentoResponseDTO convertToResponseDTO(Equipamento equipamento) {
        ClienteResponseDTO clienteDTO = null;

        if(equipamento.getCliente() != null) {
            clienteDTO = new ClienteResponseDTO(
                    equipamento.getCliente().getId(),
                    equipamento.getCliente().getNome(),
                    equipamento.getCliente().getCPF(),
                    equipamento.getCliente().getTelefone(),
                    equipamento.getCliente().getEmail()
            );
        }

        return new EquipamentoResponseDTO (
                equipamento.getId(),
                equipamento.getTipo(),
                equipamento.getMarca(),
                equipamento.getModelo(),
                equipamento.getNumeroDeSerie(),
                clienteDTO
        );

    }

}
