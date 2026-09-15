package com.cristian.cryptobackend.dto;

public record PredictionResponseDto(String coin, PredictionItem shortTerm, PredictionItem longTerm) {

    public record PredictionItem(String direction, double change, double predictedPrice, double confidence) {
    }
}
