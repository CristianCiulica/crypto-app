package com.cristian.cryptobackend.controller;

import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.PredictionResponseDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import com.cristian.cryptobackend.service.CoinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coins")
@CrossOrigin(origins = "http://localhost:5173")
public class CoinsController {

    private final CoinService service;

    public CoinsController(CoinService service) {
        this.service = service;
    }

    @GetMapping
    public List<CoinDto> getCoins(@RequestParam(defaultValue = "bitcoin,ethereum") String ids) {
        return service.getData(ids);
    }

    @GetMapping("/{id}/history")
    public List<PricePointDto> getPriceHistory(@PathVariable String id, @RequestParam int days) {
        return service.getPriceHistory(id, days);
    }

    @GetMapping("/{id}/prediction")
    public PredictionResponseDto getPrediction(@PathVariable String id) {
        return service.getPrediction(id);
    }
}
