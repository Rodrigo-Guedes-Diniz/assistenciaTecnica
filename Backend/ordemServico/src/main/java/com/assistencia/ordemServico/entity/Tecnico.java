package com.assistencia.ordemServico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_tecnico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "tecnico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemServico> ordemServico;
}
