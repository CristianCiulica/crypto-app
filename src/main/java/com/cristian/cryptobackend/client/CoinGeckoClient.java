package com.cristian.cryptobackend.client;

import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.MarketChartDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoinGeckoClient {
    private final RestClient client = RestClient.builder().baseUrl("https://api.coingecko.com/api/v3").build();
    CoinGeckoClient(){}


    public List<CoinDto> getData(String ids){
        return client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/coins/markets")
                                .queryParam("vs_currency", "usd")
                                .queryParam("ids", ids)
                                .build()
                )
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            throw new RuntimeException("CoinGecko request failed");
                        }
                )
                .body(new ParameterizedTypeReference<List<CoinDto>>() {});

    }

    public List<PricePointDto> getPriceHistory(String id, int days){
        MarketChartDto data = client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/coins/{id}/market_chart")
                                .queryParam("vs_currency", "usd")
                                .queryParam("days", days)
                                .build(id)
                )
                .retrieve()
                .body(new ParameterizedTypeReference<MarketChartDto>() {});

        List<PricePointDto> prices=new ArrayList<>();
        for(var price:data.getPrices()){
            PricePointDto pricePoint = new PricePointDto();
            pricePoint.setTime(price.get(0).longValue());
            pricePoint.setPrice(price.get(1));
            prices.add(pricePoint);
        }
        return prices;
    }
}
