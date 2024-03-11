package com.example.javamvcsample.repositories;

import com.example.javamvcsample.entities.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastRepository extends CrudRepository<Forecast, Integer> {

}
