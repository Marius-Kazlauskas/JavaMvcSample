// F4 NAVIGATE TO FILE

package com.example.javamvcsample.controllers;

import com.example.javamvcsample.models.ForecastModel;
import com.example.javamvcsample.models.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class ForecastController {
    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String city) throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");

        var forecasts = getForecasts(city);

        modelAndView.addObject("forecasts", forecasts);

        return modelAndView;
    }

    private static ArrayList<ForecastModel> getForecasts(String city) throws IOException {
        var forecasts = new ArrayList<ForecastModel>();

        if (city != null) {
            var meteoForecastsJson = GetMeteoForecastsJson(city);
            var meteoForecasts = GetObjectFromJson(meteoForecastsJson);

            for (var fr : meteoForecasts.forecastTimestamps) {
                var item = new ForecastModel(fr.forecastTimeUtc, fr.airTemperature);
                forecasts.add(item);
            }
        }

        return forecasts;
    }

    private static String GetMeteoForecastsJson(String city) throws IOException {
        URL url = new URL("https://api.meteo.lt/v1/places/" + city + "/forecasts/long-term");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        StringBuilder text = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            text.append(scanner.nextLine());
        }
        scanner.close();
        return text.toString();
    }

    //https://json2csharp.com/code-converters/json-to-pojo
    private static Root GetObjectFromJson(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Root obj = om.readValue(json, Root.class);

        return obj;
    }
}
