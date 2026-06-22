package com.katz.typeBot.dto;

import java.math.BigDecimal;

public record PerfilRequestDto(
        String nome,
        String email,
        String telefone,
        String linkedin,
        String curriculo,
        String endereco,
        String senioridade,
        BigDecimal pretensaoSalarial,
        String pcd) {}

