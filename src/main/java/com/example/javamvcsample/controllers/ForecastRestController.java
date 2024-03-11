package com.example.javamvcsample.controllers;

import com.example.javamvcsample.entities.Forecast;
import com.example.javamvcsample.models.ForecastModel;
import com.example.javamvcsample.repositories.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecastRestController {
    @Autowired
    private ForecastRepository forecastRepository;
    @PostMapping(value = "/api/forecast", consumes = "application/json")

    public void index(@RequestBody ForecastModel model) {
        System.out.println("Received date: " + model.dateTime);
        System.out.println("Received temperature: " + model.temperature);

        Forecast entity = new Forecast(
                model.dateTime,
                String.valueOf(model.temperature),
                model.city,
                1);

        forecastRepository.save(entity);
    }
}
