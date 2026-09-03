package com.cristian.cryptobackend.service;

import com.cristian.cryptobackend.client.CoinGeckoClient;
import org.springframework.stereotype.Service;

@Service
public class CoinService {
    private final CoinGeckoClient client;
    public CoinService(CoinGeckoClient client){
        this.client = client;
    }

    public String text(){
        return "CryptoAPI";
    }
}
