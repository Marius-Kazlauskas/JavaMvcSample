package com.example.javamvcsample.controllers;

import com.example.javamvcsample.models.ForecastModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastRestController {
    @PostMapping(value = "/api/forecast", consumes = "application/json")

    public void index(@RequestBody ForecastModel model) {
        System.out.println("Received date: " + model.dateTime);
        System.out.println("Received temperature: " + model.temperature);
    }
}
