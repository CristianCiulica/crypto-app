package com.cristian.cryptobackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinsController {
    @GetMapping("/api/coins")
    public String text(){
        return "CryptoAPI";
    }
}
