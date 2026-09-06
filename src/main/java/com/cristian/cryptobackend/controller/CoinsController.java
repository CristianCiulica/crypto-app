package com.cristian.cryptobackend.controller;

import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.dto.PricePointDto;
import com.cristian.cryptobackend.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CoinsController {
    private final CoinService service;

    public CoinsController(CoinService service){
        this.service=service;
    }

    @GetMapping("/api/coins/{id}/history")
    public List<PricePointDto> getPriceHistory(@PathVariable String id, @RequestParam int days){
        return service.getPriceHistory(id,days);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(500).body("Something went wrong :(");
    }
    @GetMapping("/api/coins")
    public List<CoinDto> getCoins(@RequestParam(defaultValue="bitcoin,ethereum") String ids){
        return service.getData(ids);
    }
}
