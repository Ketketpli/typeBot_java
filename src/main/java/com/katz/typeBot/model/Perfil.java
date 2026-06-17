package com.katz.typeBot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @Column
    private String nome;
    private String email;
    private String telefone;
    private String linkedin;
    private String curriculo;
    private String endereco;
    private String senioridade;
    private BigDecimal pretensaoSalarial;
    private String pcd;

}