package com.katz.typeBot.controller;

import com.katz.typeBot.dto.PerfilRequestDto;
import com.katz.typeBot.dto.PerfilResponseDto;
import com.katz.typeBot.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping
    public ResponseEntity<PerfilResponseDto> createPerfil(@Valid @RequestBody PerfilRequestDto perfilRequestDto) {
        PerfilResponseDto newPerfil = perfilService.createPerfil(perfilRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerfil);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PerfilRequestDto> updatePerfil(@PathVariable Long id, @RequestBody PerfilRequestDto perfilRequestDto) {
        PerfilRequestDto updatedPerfil = perfilService.updatePerfil(id, perfilRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPerfil);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        perfilService.deletePerfilById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<PerfilResponseDto>> getPerfil() {
        return ResponseEntity.status(HttpStatus.OK).body(perfilService.getPerfil());
    }

    @GetMapping(value = "/detalhado")
    public ResponseEntity<List<PerfilRequestDto>> getPerfilDetalhado() {
        return ResponseEntity.status(HttpStatus.OK).body(perfilService.getPerfilDetalhado());
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<PerfilResponseDto> getByEmail(@Valid @PathVariable String email) {
        PerfilResponseDto emailPerfil = perfilService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(emailPerfil);
    }

}
