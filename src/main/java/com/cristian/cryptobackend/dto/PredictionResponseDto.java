package com.cristian.cryptobackend.dto;

public class PredictionResponseDto {
    private String coin;
    private PredictionItem shortTerm;
    private PredictionItem longTerm;

    public PredictionResponseDto() {}

    public static class PredictionItem {
        private String direction;
        private double changes;
        private double predictedPrice;
        private double confidence;

        public PredictionItem() {}

        public String getDirection() { return direction; }
        public void setDirection(String direction) { this.direction = direction; }

        public double getChange() { return changes; }
        public void setChange(double change) { this.changes = change; }

        public double getPredictedPrice() { return predictedPrice; }
        public void setPredictedPrice(double predictedPrice) { this.predictedPrice = predictedPrice; }

        public double getConfidence() { return confidence; }
        public void setConfidence(double confidence) { this.confidence = confidence; }
    }

    public String getCoin() { return coin; }
    public void setCoin(String coin) { this.coin = coin; }

    public PredictionItem getShortTerm() { return shortTerm; }
    public void setShortTerm(PredictionItem shortTerm) { this.shortTerm = shortTerm; }

    public PredictionItem getLongTerm() { return longTerm; }
    public void setLongTerm(PredictionItem longTerm) { this.longTerm = longTerm; }
}