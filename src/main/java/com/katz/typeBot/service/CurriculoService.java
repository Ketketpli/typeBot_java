package com.katz.typeBot.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurriculoService {

    public String extrairPdf(InputStream arquivo) {
        try(PDDocument document = Loader.loadPDF(arquivo.readAllBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String extrairDocx(InputStream arquivo) {
        try (XWPFDocument document = new XWPFDocument(arquivo);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            return extractor.getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

// Configuracao necessaria para que o groq possa receber os dados extraidos

    private final WebClient webClient;

    @Value("${groq.api.key}")
    private String groqApiKey;


// Metodo que ira converter os dados em json depois de passar pelo groq

    public String processarCurriculo(String textoCurriculo) {
        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama3-8b-8192");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", """
        Analise o currículo abaixo e extraia as informações em JSON com exatamente esses campos:
        {
            "nome": "",
            "email": "",
            "telefone": "",
            "linkedin": "",
            "endereco": "",
            "senioridade": "",
            "pretensaoSalarial": "",
            "pcd": "",
            "habilidades": [],
            "idiomas": [],
            "experiencias": [],
            "certificacoes": [],
            "cursos": []
        }
        Retorne APENAS o JSON, sem texto adicional.
        
        Currículo:
        """ + textoCurriculo);

        body.put("messages", List.of(message));

        return webClient.post()
                .uri("https://api.groq.com/openai/v1/chat/completions")
                .header("Authorization", "Bearer " + groqApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
