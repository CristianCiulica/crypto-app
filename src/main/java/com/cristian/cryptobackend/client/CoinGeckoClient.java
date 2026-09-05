package com.cristian.cryptobackend.client;

import com.cristian.cryptobackend.dto.CoinDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

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
}
