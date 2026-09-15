package com.cristian.cryptobackend.dto;

public record CoinDto(String id, String symbol, String name,
                      double current_price, Long market_cap,
                      double price_change_percentage_24h) {
}
