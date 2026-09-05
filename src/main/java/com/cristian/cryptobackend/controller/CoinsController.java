package com.cristian.cryptobackend.controller;

import com.cristian.cryptobackend.dto.CoinDto;
import com.cristian.cryptobackend.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinsController {
    private final CoinService service;

    public CoinsController(CoinService service){
        this.service=service;
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @GetMapping("/api/coins")
    public List<CoinDto> getCoins(@RequestParam(defaultValue="bitcoin,ethereum") String ids){
        return service.getData(ids);
    }
}
