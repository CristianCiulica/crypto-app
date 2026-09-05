package com.cristian.cryptobackend.client;

import com.cristian.cryptobackend.dto.CoinDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class CoinGeckoClient {
    private final RestClient client = RestClient.builder().baseUrl("https://api.coingecko.com/api/v3").build();
    CoinGeckoClient(){}
    public List<CoinDto> getData(String ids){
        System.out.println(ids);
        return client.get()
                .uri("/coins/markets?vs_currency=usd&ids="+ids)
                .retrieve()
                .body(new ParameterizedTypeReference<List<CoinDto>>() {});

    }
}
