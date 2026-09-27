package com.assistencia.ordemServico.service;

import com.assistencia.ordemServico.dto.TecnicoRequestDTO;
import com.assistencia.ordemServico.dto.TecnicoResponseDTO;
import com.assistencia.ordemServico.entity.Tecnico;
import com.assistencia.ordemServico.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    //Criar Tecnico
    public TecnicoResponseDTO criarTecnico(TecnicoRequestDTO dto) {
        Tecnico novoTecnico = new Tecnico();

        novoTecnico.setNome(dto.nome());
        novoTecnico.setEspecialidade(dto.especialidade());
        novoTecnico.setEmail(dto.email());

        Tecnico tecnico = tecnicoRepository.save(novoTecnico);

        return convertToResponseDTO(tecnico);
    }

    //Listar todos os tecnicos
    public List<TecnicoResponseDTO> listarTodosTecnicos() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();
        return tecnicos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }


    //Buscar tecncio por Id
    public TecnicoResponseDTO buscarTecnicoPorId(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tecnico nao encontrado com id " + id));

        return convertToResponseDTO(tecnico);
    }


    //Editar tecnico
    public TecnicoResponseDTO editarTecnico(Long id, TecnicoRequestDTO dto) {
        Tecnico novoTecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tecnico nao encontrado con id " + id));

        novoTecnico.setNome(dto.nome());
        novoTecnico.setEspecialidade(dto.especialidade());
        novoTecnico.setEmail(dto.email());

        Tecnico tecnicoAtualizado = tecnicoRepository.save(novoTecnico);

        return convertToResponseDTO(tecnicoAtualizado);
    }

    //Deletar tecnico
    public String deletarTecnico(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("tecnico nao encontrado com id " + id));


        tecnicoRepository.delete(tecnico);
        return "Tecnico com id " + id + " deletado com sucesso";
    }


    //Converter para TecnicoResponseDTO
    public TecnicoResponseDTO convertToResponseDTO(Tecnico tecnico){
        return new TecnicoResponseDTO(
                tecnico.getId(),
                tecnico.getNome(),
                tecnico.getEspecialidade(),
                tecnico.getEmail()
        );
    }

}
