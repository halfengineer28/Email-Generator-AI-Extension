package com.email.emailwriter.service;

import com.email.emailwriter.dto.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class EmailGeneratorService {

    // This pulls the value from your application.properties file
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public String generateEmailReply(EmailRequestDTO emailRequestDTO) {
        String prompt = buildPrompt(emailRequestDTO);

        // Pass the key explicitly here during client initialization
        Client client = Client.builder()
                .apiKey(geminiApiKey)
                .build();

        // Fix: Use the 'prompt' variable instead of "" and null
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null);

        return response.text();
    }


    private String extractResponseContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

        } catch (Exception e) {
            return "Error processing request: " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequestDTO emailRequestDTO){
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please do not generate a subject line");
        if(emailRequestDTO.getTone() != null && !emailRequestDTO.getTone().isEmpty()){
            prompt.append("Use a").append(emailRequestDTO.getTone()).append("tone");
        }
        prompt.append("\nOriginal email : \n").append(emailRequestDTO.getEmailContent());
        return prompt.toString();
    }
}
