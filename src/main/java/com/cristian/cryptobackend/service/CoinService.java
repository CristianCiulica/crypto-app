package com.cristian.cryptobackend.service;

import com.cristian.cryptobackend.client.CoinGeckoClient;
import com.cristian.cryptobackend.dto.CoinDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {
    private final CoinGeckoClient client;
    public CoinService(CoinGeckoClient client){
        this.client = client;
    }

    public List<CoinDto> getData(){
        return client.getData();
    }
}
