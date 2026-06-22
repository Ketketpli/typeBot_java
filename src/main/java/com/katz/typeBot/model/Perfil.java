package com.katz.typeBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "perfis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "perfil")
    private List<HabilidadesTecnicas> habilidades = new ArrayList<>();
    @OneToMany(mappedBy = "perfil")
    private List<Idiomas> idiomas = new ArrayList<>();
    @OneToMany(mappedBy = "perfil")
    private List<Experiencias> experiencias = new ArrayList<>();
    @OneToMany(mappedBy = "perfil")
    private List<Certificacoes> certificacoes = new ArrayList<>();
    @OneToMany(mappedBy = "perfil")
    private List<CursosComplementares> cursos = new ArrayList<>();

    private String nome;

    private String email;

    private String telefone;

    private String linkedin;

    private String curriculo;

    private String endereco;

    private String senioridade;

    @Column(name = "pretensao_salarial")
    private BigDecimal pretensaoSalarial;

    private String pcd;

    @Enumerated(EnumType.STRING)
    private StatusCandidato status;

}