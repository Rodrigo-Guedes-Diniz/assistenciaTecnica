package com.assistencia.ordemServico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "CPF", nullable = false, unique = true)
    private String CPF;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipamento> equipamentos;

}
