package com.katz.typeBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "habilidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabilidadesTecnicas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    private String habilidades;

    @Column(name = "proeficiencia_habilidade")
    private String proeficienciaHabilidade;
}
