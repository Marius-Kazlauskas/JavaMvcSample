// https://dev.mysql.com/downloads/mysql/
// https://www.mysql.com/products/workbench/
// Naudojau mysql server 8.0 versiją, nes buvo niuansas, kad workbench su 8.3 (naujausia) neveikė
// Sukurti nauja forecastdb ir paleisti programą - turėtų forecastdb automtiškai atsirasti nauje lentelė Forecast

package com.example.javamvcsample.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer id;
    public String date;
    public String temperature;
    public String city;
    public int userId;

    public Forecast() {
    }

    public Forecast(String date, String temperature, String city, int userId) {
        this.date = date;
        this.temperature = temperature;
        this.city = city;
        this.userId = userId;
    }
}
