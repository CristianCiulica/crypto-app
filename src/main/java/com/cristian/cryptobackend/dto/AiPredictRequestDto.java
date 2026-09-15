package com.cristian.cryptobackend.dto;

import java.util.List;

public record AiPredictRequestDto(String coin, List<Double> shortTermPrices, List<Double> longTermPrices) {
}
