package com.cristian.cryptobackend.client;

import com.cristian.cryptobackend.dto.CoinDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class CoinGeckoClient {
    private final RestClient client = RestClient.builder().build();
    CoinGeckoClient(){}

    public List<CoinDto> getData(){
        return client.get()
                .uri("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=bitcoin,ethereum")
                .retrieve()
                .body(new ParameterizedTypeReference<List<CoinDto>>() {});

    }
}
