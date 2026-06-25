package com.katz.typeBot.controller;

import com.katz.typeBot.service.CurriculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/curriculo")
@RequiredArgsConstructor
public class CurriculoController {

    private final CurriculoService curriculoService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> extrairCurriculo(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {
        String nomeArqivo = arquivo.getOriginalFilename();
        String texto = null;

        if(nomeArqivo.endsWith(".pdf")) {
            texto = curriculoService.extrairPdf(arquivo.getInputStream());
        }
        else if (nomeArqivo.endsWith(".docx")) {
            texto = curriculoService.extrairDocx(arquivo.getInputStream());
        }

        String resultado = curriculoService.processarCurriculo(texto);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }
}
