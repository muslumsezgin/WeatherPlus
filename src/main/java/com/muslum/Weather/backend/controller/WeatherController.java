package com.muslum.Weather.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.muslum.Weather.backend.model.ResponseData;
import com.muslum.Weather.backend.service.WeatherService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.Objects;

@Service
public class WeatherController {

    private static final Logger logger = Logger.getLogger(WeatherController.class);
    private static final Gson gson = new GsonBuilder().create();

    public ResponseData getServices(String cityName) {
        //String appID = "bd5e378503939ddaee76f12ad7a97608";
        HttpURLConnection connection = null;
        String tmpStr;
        String response = null;

        logger.info("Looking for ' " + cityName + " ' city.");

        response = WeatherService.getData(cityName, connection, response);

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