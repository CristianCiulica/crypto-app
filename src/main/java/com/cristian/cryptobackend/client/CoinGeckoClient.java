package com.cristian.cryptobackend.client;

import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.MarketChartDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoinGeckoClient {

    private final RestClient client;

    public CoinGeckoClient(@Value("${coingecko.base-url}") String baseUrl) {
        this.client = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public List<CoinDto> getData(String ids) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/coins/markets")
                        .queryParam("vs_currency", "usd")
                        .queryParam("ids", ids)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new RuntimeException("CoinGecko error: " + response.getStatusCode());
                })
                .body(new ParameterizedTypeReference<List<CoinDto>>() {});
    }

    public List<PricePointDto> getPriceHistory(String id, int days) {
        MarketChartDto data = client.get()
                .uri(uriBuilder -> {
                    var builder = uriBuilder
                            .path("/coins/{id}/market_chart")
                            .queryParam("vs_currency", "usd")
                            .queryParam("days", days);

                    // Dacă cerem date pe termen lung (> 90 zile), CoinGecko are nevoie de interval zilnic
                    if (days > 90) {
                        builder = builder.queryParam("interval", "daily");
                    }

                    return builder.build(id);
                })
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new RuntimeException("CoinGecko history error: " + response.getStatusCode());
                })
                .body(MarketChartDto.class);

        List<PricePointDto> prices = new ArrayList<>();
        if (data != null && data.getPrices() != null) {
            for (var price : data.getPrices()) {
                PricePointDto pricePoint = new PricePointDto();
                pricePoint.setTime(price.get(0).longValue());
                pricePoint.setPrice(price.get(1));
                prices.add(pricePoint);
            }
        }
        return prices;
    }
}