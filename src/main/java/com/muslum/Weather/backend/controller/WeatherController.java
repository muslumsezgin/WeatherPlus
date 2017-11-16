package com.muslum.Weather.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.muslum.Weather.Constant.Constant;
import com.muslum.Weather.backend.model.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Controller
public class WeatherController {

    private static final Logger logger = Logger.getLogger(WeatherController.class);
    private static final Gson gson = new GsonBuilder().create();

    public ResponseData getServices(String name) {
        //String appID = "bd5e378503939ddaee76f12ad7a97608";
        HttpURLConnection connection = null;
        String tmpStr;
        String response = null;

        logger.info("Looking for ' " + name + " ' city.");

        try {
            URL request = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + name + "&units=Metric&cnt=7" + "&mode=json&APPID=" + Constant.OpenWeatherAPI);
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

        return fromJson(response, ResponseData.class);
    }

    private <T> T fromJson(String string, Class<T> clazz) {
        T returnedClass = null;
        try {
            if (Objects.nonNull(string) && Objects.nonNull(clazz)) {
                returnedClass = gson.fromJson(string, clazz);
                logger.info(returnedClass.toString());
            }
        } catch (JsonSyntaxException e) {
            logger.error(e.getMessage(), e);
        }

        return returnedClass;
    }
}