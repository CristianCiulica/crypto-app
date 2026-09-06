package com.cristian.cryptobackend.dto;

public class PricePointDto {
    private double price;
    private Long time;

    public double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
