package com.cristian.cryptobackend.client;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CoinGeckoClient {
    private final RestClient client = RestClient.builder().build();
    CoinGeckoClient(){
    }

}
