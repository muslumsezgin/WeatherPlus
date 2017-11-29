package com.muslum.Weather.backend.service;

import com.muslum.Weather.Constant.Constant;
import com.muslum.Weather.backend.controller.WeatherController;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final Logger logger = Logger.getLogger(WeatherService.class);

    public static String getData(String cityName, HttpURLConnection connection, String response) {
        String tmpStr;
        try {
            URL request = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + cityName + "&units=Metric&cnt=7" + "&mode=json&APPID=" + Constant.OpenWeatherAPI);
            connection = (HttpURLConnection) request.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((tmpStr = reader.readLine()) != null) response = tmpStr;
                reader.close();
            }
            connection.disconnect();

        } catch (IOException e) {
            if (connection != null) {
                connection.disconnect();
            }
            logger.error(e.getMessage(), e);
        }
        return response;
    }
}
