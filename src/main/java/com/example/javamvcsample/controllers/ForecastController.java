// F4 NAVIGATE TO FILE

package com.example.javamvcsample.controllers;

import com.example.javamvcsample.models.ForecastModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Console;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class ForecastController {
    @GetMapping("/")
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");

        var forecasts = getForecasts();
        var meteoForecastsJson = GetMeteoForecastsJson();
        System.out.println(meteoForecastsJson);

        modelAndView.addObject("forecasts", forecasts);

        return modelAndView;
    }

    private static ArrayList<ForecastModel> getForecasts() {
        var forecasts = new ArrayList<ForecastModel>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var f1 = new ForecastModel(LocalDateTime.now().format(formatter), 1.0);
        var f2 = new ForecastModel(LocalDateTime.now().format(formatter), 2.0);
        var f3 = new ForecastModel(LocalDateTime.now().format(formatter), 3.0);
        forecasts.add(f1);
        forecasts.add(f2);
        forecasts.add(f3);
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
}
