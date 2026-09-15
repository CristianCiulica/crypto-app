package com.cristian.cryptobackend.dto;

import java.util.List;

public class AiPredictRequestDto {
    private String coin;
    private List<Double> shortTermPrices;
    private List<Double> longTermPrices;

    public AiPredictRequestDto() {}

    public AiPredictRequestDto(String coin, List<Double> shortTermPrices, List<Double> longTermPrices) {
        this.coin = coin;
        this.shortTermPrices = shortTermPrices;
        this.longTermPrices = longTermPrices;
    }

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public List<Double> getShortTermPrices() { return shortTermPrices; }
    public void setShortTermPrices(List<Double> shortTermPrices) { this.shortTermPrices = shortTermPrices; }

    public List<Double> getLongTermPrices() { return longTermPrices; }
    public void setLongTermPrices(List<Double> longTermPrices) { this.longTermPrices = longTermPrices; }
}