package com.katz.typeBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "certificacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Certificacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @Column(name = "nome_certificacao")
    private String nomeCertificacao;

    @Column(name = "instituicao_da_certificacao")
    private String instituicaoDaCertificacao;

    @Column(name = "data_da_certificacao")
    private String dataDaCertificacao;
}
