package com.assistencia.ordemServico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tecnico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome")
    String nome;

    @Column(name = "especialidade")
    String especialidade;

    @Column(name = "email")
    String email;


}
