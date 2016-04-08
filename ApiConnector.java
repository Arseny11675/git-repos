package com.company;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
//import com.sun.javafx.fxml.builder.URLBuilder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
* Соединяется с OpenWeatherMap API и получает прогноз погоды
* Использует HTTP
* */

public class ApiConnector {

    private static final String APP_ID = "c36acd11367419ca8de1fced8799e41c";

    public String getForecastForToday(String city) throws IOException {
        URL address = null;
        try {
            String endpointWithParams = String.format("/data/2.5/weather?q=%s&appid=%s&units=%s", city, APP_ID, "metric");
            address = new URL("http", "api.openweathermap.org", endpointWithParams);
        } catch (MalformedURLException e) {
            System.err.println(e.toString());
            System.exit(0);
        }

        URLConnection connection = address.openConnection();
        StringBuilder result = new StringBuilder();
        try (InputStream is = connection.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        JsonParser parser1 = new JsonParser();
        JsonElement mainElement1 = parser1.parse(result.toString());
        JsonObject rootObject1 = mainElement1.getAsJsonObject();
        JsonObject mainObject1 = rootObject1.getAsJsonObject("main");

        String resultString1 = String.format(
                "Temperature today is %s, pressure %s",
                mainObject1.get("temp").getAsString(),
                mainObject1.get("pressure").getAsString()
                );

        return resultString1;
    }
    
    public String getForecastForTomorrow(String city) throws IOException {
        URL address = null;
        try {
            String endpointWithParams = String.format("/data/2.5/forecast?q=%s&appid=%s&units=%s", city, APP_ID, "metric");
            address = new URL("http", "api.openweathermap.org", endpointWithParams);
        } catch (MalformedURLException e) {
            System.err.println(e.toString());
            System.exit(0);
        }

        URLConnection connection =  address.openConnection();
        StringBuilder result = new StringBuilder();
        try (InputStream is = connection.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        JsonParser parser2 = new JsonParser();
        JsonElement mainElement2 = parser2.parse(result.toString());
        JsonObject rootObject2 = (JsonObject)mainElement2;
        JsonObject obj = rootObject2.get("list").getAsJsonArray().get(8).getAsJsonObject().get("main").getAsJsonObject();
        double temp = obj.get("temp").getAsDouble();
        double pressure = obj.get("pressure").getAsDouble();

        String resultString2 = String.format("Temperature tommorow will be %s, pressure %s", temp, pressure);
        
        return resultString2;
    }

}
