package com.katz.typeBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "idiomas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Idiomas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @Column(name = "nome_idiomas")
    private String nomeIdiomas;

    @Column(name = "proeficiencia_idioma")
    private String proeficienciaIdioma;

}
