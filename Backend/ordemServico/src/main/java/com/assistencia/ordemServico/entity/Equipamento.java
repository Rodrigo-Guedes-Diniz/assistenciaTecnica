package com.assistencia.ordemServico.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_equipamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "nomeroDeSerie")
    private Long numeroDeSerie;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
