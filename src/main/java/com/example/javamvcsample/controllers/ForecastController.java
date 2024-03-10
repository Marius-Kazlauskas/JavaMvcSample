// F4 NAVIGATE TO FILE

package com.example.javamvcsample.controllers;

import com.example.javamvcsample.models.ForecastModel;
import com.example.javamvcsample.models.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class ForecastController {
    @GetMapping("/")
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");

        var forecasts = getForecasts();

        modelAndView.addObject("forecasts", forecasts);

        return modelAndView;
    }

    private static ArrayList<ForecastModel> getForecasts() throws IOException {
        var meteoForecastsJson = GetMeteoForecastsJson();
        var meteoForecasts = GetObjectFromJson(meteoForecastsJson);

        var forecasts = new ArrayList<ForecastModel>();
        for (var fr : meteoForecasts.forecastTimestamps)
        {
            var item = new ForecastModel(fr.forecastTimeUtc, fr.airTemperature);
            forecasts.add(item);
        }

        return forecasts;
    }

    private static String GetMeteoForecastsJson() throws IOException {
        URL url = new URL("https://api.meteo.lt/v1/places/vilnius/forecasts/long-term");

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
