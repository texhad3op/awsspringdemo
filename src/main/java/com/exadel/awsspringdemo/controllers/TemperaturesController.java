package com.exadel.awsspringdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemperaturesController {

    @GetMapping("/")
    public String temperatures() {
        return "temperatures";
    }
}
