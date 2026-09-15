package com.cristian.cryptobackend.client;

import com.cristian.cryptobackend.dto.AiPredictRequestDto;
import com.cristian.cryptobackend.dto.PredictionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiServiceClient {
    private final RestClient client;

    public AiServiceClient(@Value("${ai-service.url}") String aiUrl) {
        this.client = RestClient.builder()
                .baseUrl(aiUrl)
                .build();
    }

    public PredictionResponseDto predict(AiPredictRequestDto payload) {
        return client.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new RuntimeException("AI service error: HTTP " + response.getStatusCode());
                })
                .body(PredictionResponseDto.class);
    }

}
