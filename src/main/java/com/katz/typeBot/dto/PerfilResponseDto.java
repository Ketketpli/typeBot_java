package com.katz.typeBot.dto;

import com.katz.typeBot.model.StatusCandidato;

public record PerfilResponseDto(
        Long id,
        String nome,
        String email,
        String telefone,
        StatusCandidato status) {}
