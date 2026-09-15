package com.cristian.cryptobackend.service;

import com.cristian.cryptobackend.client.AiServiceClient;
import com.cristian.cryptobackend.client.CoinGeckoClient;
import com.cristian.cryptobackend.dto.AiPredictRequestDto;
import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.PredictionResponseDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {

    private final CoinGeckoClient geckoClient;
    private final AiServiceClient aiClient;

    public CoinService(CoinGeckoClient geckoClient, AiServiceClient aiClient) {
        this.geckoClient = geckoClient;
        this.aiClient = aiClient;
    }

    public List<CoinDto> getData(String ids) {
        return geckoClient.getData(ids);
    }

    public List<PricePointDto> getPriceHistory(String id, int days) {
        return geckoClient.getPriceHistory(id, days);
    }

    public PredictionResponseDto getPrediction(String coinId) {
        List<PricePointDto> shortHistory = geckoClient.getPriceHistory(coinId, 7);
        List<PricePointDto> longHistory = geckoClient.getPriceHistory(coinId, 365);
        List<Double> shortPrices = shortHistory.stream()
                .map(PricePointDto::getPrice)
                .toList();

        List<Double> longPrices = longHistory.stream()
                .map(PricePointDto::getPrice)
                .toList();

        AiPredictRequestDto requestPayload = new AiPredictRequestDto(coinId, shortPrices, longPrices);

        return aiClient.predict(requestPayload);
    }
}