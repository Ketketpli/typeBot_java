package com.katz.typeBot.controller;

import com.katz.typeBot.dto.PerfilRequestDto;
import com.katz.typeBot.dto.PerfilResponseDto;
import com.katz.typeBot.service.PerfilService;
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
    public ResponseEntity<PerfilResponseDto> createPerfil(@RequestBody PerfilRequestDto perfilRequestDto) {
        PerfilResponseDto response = perfilService.createPerfil(perfilRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilRequestDto> updatePerfil(@PathVariable Long id,
                                                         @RequestBody PerfilRequestDto perfilRequestDto) {
        PerfilRequestDto response = perfilService.updatePerfil(id, perfilRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        perfilService.deletePerfilById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PerfilResponseDto>> getPerfis() {
        List<PerfilResponseDto> response = perfilService.getPerfil();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PerfilResponseDto> findByEmail(@PathVariable String email) {
        PerfilResponseDto response = perfilService.findByEmail(email);
        return ResponseEntity.ok(response);
    }
}