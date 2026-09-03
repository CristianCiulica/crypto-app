package com.cristian.cryptobackend.dto;

public class CoinDto {
    private String id, symbol, name;
    private double current_price;
    private Long market_cap;
    private double price_change_percentage_24h;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getMarket_cap() {
        return market_cap;
    }
    public void setMarket_cap(Long market_cap) {
        this.market_cap = market_cap;
    }
    public double getCurrent_price() {
        return current_price;
    }
    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }
    public double getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }
    public void setPrice_change_percentage_24h(double price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }
}
