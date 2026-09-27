package com.assistencia.ordemServico.service;

import com.assistencia.ordemServico.dto.EquipamentoResponseDTO;
import com.assistencia.ordemServico.dto.OrdemServicoRequestDTO;
import com.assistencia.ordemServico.dto.OrdemServicoResponseDTO;
import com.assistencia.ordemServico.dto.TecnicoResponseDTO;
import com.assistencia.ordemServico.entity.Equipamento;
import com.assistencia.ordemServico.entity.OrdemServico;
import com.assistencia.ordemServico.entity.Tecnico;
import com.assistencia.ordemServico.repository.EquipamentoRepository;
import com.assistencia.ordemServico.repository.OrdemServicoRepository;
import com.assistencia.ordemServico.repository.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;
    private final TecnicoRepository tecnicoRepository;

    public OrdemServicoService(EquipamentoRepository equipamentoRepository, TecnicoRepository tecnicoRepository, OrdemServicoRepository ordemServicoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
    }

    //Cadastrar ordem de serico
    public OrdemServicoResponseDTO cadastrarOrdemServico(OrdemServicoRequestDTO dto, Long equipamentoId, Long tecnicoId) {
        Equipamento equipamento = equipamentoRepository.findById(equipamentoId)
                .orElseThrow(() -> new EntityNotFoundException("equipamento nao encontrado com id " + equipamentoId));

        Tecnico tecnico = null;
        if (tecnicoId != null) {
            tecnico = tecnicoRepository.findById(tecnicoId)
                    .orElseThrow(() -> new EntityNotFoundException("tecnico nao encontrado com o id " + tecnicoId));
        }

        OrdemServico novaOrdemServico = new OrdemServico();

        novaOrdemServico.setDescricao(dto.descicao());
        novaOrdemServico.setPrioridade(dto.prioridade());
        novaOrdemServico.setEquipamento(equipamento);
        novaOrdemServico.setTecnico(tecnico);

        novaOrdemServico.setData(LocalDateTime.now());
        novaOrdemServico.setStatus("AGUARDANDO TECNICO");

        OrdemServico ordemSalva = ordemServicoRepository.save(novaOrdemServico);

        return convertToResponseDTO(ordemSalva);

    }

    //Listar todas as ordens de servico
    public List<OrdemServicoResponseDTO> listarTodasOrdemsServico() {
        List<OrdemServico> ordemServicos = ordemServicoRepository.findAll();

        return ordemServicos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    //Listar ordem de servico por id
    public OrdemServicoResponseDTO buscarOrdemServicoPorId(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ordem de serviço nao encontrada com id " + id));

        return convertToResponseDTO(ordemServico);
    }

    //Editar ordem de servico
    public OrdemServicoResponseDTO editarOrdemServico(Long id, OrdemServicoRequestDTO dto) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ordem de servico nao encontrada com o id " + id));

        ordemServico.setDescricao(dto.descicao());
        ordemServico.setPrioridade(dto.prioridade());

        Equipamento equipamento = equipamentoRepository.findById(dto.equipamentoId())
                .orElseThrow(() -> new NoSuchElementException("equipamento nao encontrado com id " + dto.equipamentoId()));
        ordemServico.setEquipamento(equipamento);

        Tecnico tecnico = tecnicoRepository.findById(dto.tecnicoId())
                .orElseThrow(() -> new NoSuchElementException("tecnico nao encontrado com id " + dto.tecnicoId()));
        ordemServico.setTecnico(tecnico);

        OrdemServico ordemServicoEditada = ordemServicoRepository.save(ordemServico);

        return convertToResponseDTO(ordemServicoEditada);

    }

    //Excluir ordem de serico
    public String deletarOrdemServico(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ordem de serviço nao encontrada com id " + id));

        ordemServicoRepository.delete(ordemServico);

        return "Ordem de servico deletada com sucesso";
    }

    //Converter para ResponseDTO
    public OrdemServicoResponseDTO convertToResponseDTO(OrdemServico ordemServico) {

        EquipamentoResponseDTO equipamentoDTO = new EquipamentoResponseDTO(
                ordemServico.getEquipamento().getId(),
                ordemServico.getEquipamento().getTipo(),
                ordemServico.getEquipamento().getMarca(),
                ordemServico.getEquipamento().getModelo(),
                ordemServico.getEquipamento().getNumeroDeSerie(),
                null
        );

        TecnicoResponseDTO tecnicoDTO = null;
        if (ordemServico.getTecnico() != null) {
            tecnicoDTO = new TecnicoResponseDTO(
                    ordemServico.getTecnico().getId(),
                    ordemServico.getTecnico().getNome(),
                    ordemServico.getTecnico().getEspecialidade(),
                    ordemServico.getTecnico().getEmail()
            );
        }

        OrdemServicoResponseDTO novaOrdemServico = new OrdemServicoResponseDTO(
                ordemServico.getId(),
                ordemServico.getDescricao(),
                ordemServico.getData(),
                ordemServico.getStatus(),
                ordemServico.getPrioridade(),
                equipamentoDTO,
                tecnicoDTO
        );

        return novaOrdemServico;

    }

}