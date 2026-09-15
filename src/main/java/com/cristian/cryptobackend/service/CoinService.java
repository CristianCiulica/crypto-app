package com.cristian.cryptobackend.service;

import com.cristian.cryptobackend.dto.AiPredictRequestDto;
import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.MarketChartDto;
import com.cristian.cryptobackend.dto.PredictionResponseDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoinService {

    private final RestClient geckoClient;
    private final RestClient aiClient;

    public CoinService(@Value("${coingecko.base-url}") String geckoUrl,
                       @Value("${ai-service.url}") String aiUrl) {
        this.geckoClient = RestClient.create(geckoUrl);
        this.aiClient = RestClient.create(aiUrl);
    }

    public List<CoinDto> getData(String ids) {
        CoinDto[] coins = geckoClient.get()
                .uri("/coins/markets?vs_currency=usd&ids={ids}", ids)
                .retrieve()
                .body(CoinDto[].class);
        return List.of(coins);
    }

    public List<PricePointDto> getPriceHistory(String id, int days) {
        String interval = days > 90 ? "&interval=daily" : "";
        MarketChartDto data = geckoClient.get()
                .uri("/coins/{id}/market_chart?vs_currency=usd&days={days}" + interval, id, days)
                .retrieve()
                .body(MarketChartDto.class);

        List<PricePointDto> prices = new ArrayList<>();
        for (List<Double> point : data.prices()) {
            prices.add(new PricePointDto(point.get(0).longValue(), point.get(1)));
        }
        return prices;
    }

    public PredictionResponseDto getPrediction(String coinId) {
        List<Double> shortPrices = getPriceHistory(coinId, 7).stream().map(PricePointDto::price).toList();

        // CoinGecko free tier: ~10 req/min — space out calls to avoid 429
        try { Thread.sleep(1500); } catch (InterruptedException ignored) { }

        List<Double> longPrices = getPriceHistory(coinId, 365).stream().map(PricePointDto::price).toList();

        return aiClient.post()
                .uri("/predict")
                .body(new AiPredictRequestDto(coinId, shortPrices, longPrices))
                .retrieve()
                .body(PredictionResponseDto.class);
    }
}
