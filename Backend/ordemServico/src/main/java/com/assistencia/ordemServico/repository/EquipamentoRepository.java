package com.assistencia.ordemServico.repository;

import com.assistencia.ordemServico.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> encontrarPorCliente(Long clienteId);

}
