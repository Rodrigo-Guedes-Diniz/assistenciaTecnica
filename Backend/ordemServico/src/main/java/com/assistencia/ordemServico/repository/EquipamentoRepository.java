package com.assistencia.ordemServico.repository;

import com.assistencia.ordemServico.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    List<Equipamento> findByCliente(Long clienteId);

}
