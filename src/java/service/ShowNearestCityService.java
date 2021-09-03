/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.CityModel;
import model.CountryModel;

/**
 *
 * @author Dao Van Do
 */
public class ShowNearestCityService {

    public CityModel getNearestCityByGPS(String lat, String lon) throws IOException {
        CityModel cityModel = new CityModel();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.airvisual.com/v2/nearest_city?lat=" + lat + "&lon=" + lon + "&key=8722b546-3630-4434-9bad-f77ae927f5e2")
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();

            String city = jsonObject.get("data").getAsJsonObject().get("city").toString();
            city = city.replaceAll("\"", "");
            String state = jsonObject.get("data").getAsJsonObject().get("state").toString();
            state = state.replaceAll("\"", "");
            String country = jsonObject.get("data").getAsJsonObject().get("country").toString();
            country = country.replaceAll("\"", "");

            cityModel.setCity(city);
            cityModel.setState(state);
            cityModel.setCountry(country);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityModel;

    }

    public CountryModel getNearestCityByName(String name) throws IOException {
        CountryModel countryModel = new CountryModel();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.airvisual.com/v2/states?country=" + name + "&key=8722b546-3630-4434-9bad-f77ae927f5e2")
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
            List<String> state = new ArrayList<>();
            int size = jsonObject.get("data").getAsJsonArray().size();
            for (int i = 0; i < size; i++) {
                state.add(jsonObject.get("data").getAsJsonArray().get(i).getAsJsonObject().get("state").toString());
            }

            countryModel.setState(state.toString().replaceAll("[\\[\\](){}]", ""));
        } catch (Exception e) {
            countryModel.setState("Error!!!");
            e.printStackTrace();
        }
        countryModel.setCity(name);
        return countryModel;

    }

    private List<String> getListCountry() throws IOException {
        List<String> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.airvisual.com/v2/countries?key=8722b546-3630-4434-9bad-f77ae927f5e2")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
            int size = jsonObject.get("data").getAsJsonArray().size();
            for (int i = 0; i < size; i++) {
                list.add(jsonObject.get("data").getAsJsonArray().get(i).getAsJsonObject().get("country").toString().replaceAll("\"", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CountryModel> getAllCountry(int startIndex, int endindex) throws IOException {
        List<CountryModel> cityModels = new ArrayList<>();
        List<String> listCountry = getListCountry();
        if (listCountry.size() == 0) {
            return cityModels;
        }
        for (int i = startIndex; i < endindex; i++) {
            cityModels.add(getNearestCityByName(listCountry.get(i)));
        }
        return cityModels;
    }

    public List<String> getListStateByCountry(String country) {

        List<String> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.airvisual.com/v2/states?country=Japan&key=8722b546-3630-4434-9bad-f77ae927f5e2")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
            int size = jsonObject.get("data").getAsJsonArray().size();
            for (int i = 0; i < size; i++) {
                list.add(jsonObject.get("data").getAsJsonArray().get(1).getAsJsonObject().get("state").toString().replaceAll("\"", ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
