package com.cristian.cryptobackend.service;

import com.cristian.cryptobackend.client.CoinGeckoClient;
import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoinService {
    private final CoinGeckoClient client;
    public CoinService(CoinGeckoClient client){
        this.client = client;
    }

    public List<PricePointDto> getPriceHistory(String id, int days){
        return client.getPriceHistory(id,days);
    }

    public List<CoinDto> getData(String ids){
        return client.getData(ids);
    }
}
