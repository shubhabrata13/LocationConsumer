package com.apmm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class health {

    @GetMapping("/health")
    public String greeting() {
        return "hello from consumer";
    }
}
