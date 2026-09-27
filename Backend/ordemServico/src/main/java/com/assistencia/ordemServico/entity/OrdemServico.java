package com.assistencia.ordemServico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_ordemServico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "status")
    private String status;

    @Column(name = "propriedade")
    private Boolean prioridade;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

}
