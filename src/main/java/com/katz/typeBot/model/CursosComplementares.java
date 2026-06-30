package com.katz.typeBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursosComplementares implements PerfilAssociavel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @Column(name = "nome_curso")
    private String nomeCurso;

    @Column(name = "instituicao_do_curso")
    private String instituicaoDoCurso;

    @Column(name = "data_conclusao_curso")
    private String dataConclusaoCurso;
}
