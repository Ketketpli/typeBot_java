package com.katz.typeBot.service;

import com.katz.typeBot.exceptions.JsonProcessingException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
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

    private WebClient webClient;

    public CurriculoService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${groq.api.key}")
    private String groqApiKey;


// Método que utiliza o Groq para converter os dados extraidos em Json

    public String processarCurriculo(String textoCurriculo) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("model", "llama-3.3-70b-versatile");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", """
                Analise o currículo abaixo e extraia as informações.
                        
                Retorne APENAS um JSON válido.
                                        
                Regras:
                - Não utilize markdown.
                - Não utilize blocos ```json.
                - Não utilize explicações.
                - Não utilize comentários.
                - Caso um campo não seja encontrado, retorne string vazia "".
                - Para listas sem conteúdo, retorne [].
                - O JSON deve seguir exatamente esta estrutura:
                                                                        
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

        String response = webClient.post()
                .uri("https://api.groq.com/openai/v1/chat/completions")
                .header("Authorization", "Bearer " + groqApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.path("choices").get(0).path("message").path("content").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
