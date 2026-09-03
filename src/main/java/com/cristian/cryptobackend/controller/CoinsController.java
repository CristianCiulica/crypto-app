package com.cristian.cryptobackend.controller;

import com.cristian.cryptobackend.service.CoinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinsController {
    private final CoinService service;

    public CoinsController(CoinService service){
        this.service=service;
    }

    @GetMapping("/api/coins")
    public String text(){
        return service.text();
    }
}
