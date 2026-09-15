package com.cristian.cryptobackend.dto;

import java.util.List;

public record MarketChartDto(List<List<Double>> prices) {
}
