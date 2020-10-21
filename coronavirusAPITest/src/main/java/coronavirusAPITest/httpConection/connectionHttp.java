package coronavirusAPITest.httpConection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.ObjectMapper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import coronavirusAPITest.Model.Country;
import coronavirusAPITest.Model.CovidStat;
import coronavirusAPITest.Model.Data;
import coronavirusAPITest.Model.Province;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@Service
@Qualifier("connect")
public class connectionHttp implements connectionHttpInterface{

    @Override
    public HashMap<String, Country> getAllStatistics() throws Exception {

        Request request = request = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/v1/stats")
                .get()
                .addHeader("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8012606648msh1039fb7043b7860p161694jsn207c378a1e89")
                .build();

        OkHttpClient cliente = new OkHttpClient();
        String respuestaApi = null;
        Response respuesta = null;

        try {
            respuesta = cliente.newCall(request).execute();
            if (respuesta.isSuccessful()) {
                respuestaApi= respuesta.body().string();
            }
        } catch (IOException e) {
            throw new Exception("Not Service Found ");
        }

        Gson gson = new Gson();
        Data stats=new Data();
        HashMap<String ,Country> countryDictionary=new HashMap<String ,Country>();
        stats = gson.fromJson(respuestaApi,Data.class);

        for (CovidStat stat:stats.getData().getCovid19Stats()) { // ciclo de los elementos(CovidStat) de la lista covid19stats de la clase CovidStats
            if(!(countryDictionary.containsKey(stat.getCountry()))){
                countryDictionary.put(stat.getCountry(),new Country(stat.getCountry()));
            }
            countryDictionary.get(stat.getCountry()).addStat(stat);
        }
        return countryDictionary;
    }

    @Override
    public List<CovidStat> getStatisticsByCountryName(String country) throws Exception {


        Request request = request = new Request.Builder()
                .url("https://rapidapi.p.rapidapi.com/v1/stats?country="+country)
                .get()
                .addHeader("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8012606648msh1039fb7043b7860p161694jsn207c378a1e89")
                .build();

        OkHttpClient cliente = new OkHttpClient();
        String respuestaApi = null;
        Response respuesta = null;
        try {
            respuesta = cliente.newCall(request).execute();
            if (respuesta.isSuccessful()) {
                respuestaApi= respuesta.body().string();
            }
        } catch (IOException e) {
            throw new Exception("Not Service Found ");
        }
        if(respuesta.message().equals("Country not found. Returning all stats. Please use a country name found in the data property.")){
            throw new Exception("Country not found.");
        }
        /*System.out.println("legooooooooooooooooooooooooooooooooooooooo");*/
        Gson gson = new Gson();
        Data stats=new Data();
        stats = gson.fromJson(respuestaApi,Data.class);
        System.out.println(stats.getData().getCovid19Stats());
        return stats.getData().getCovid19Stats();
    }
//    private Hashtable<String,Country> jsonToObject(JSONArray jsonObject){
//
//        Hashtable<String,Country> countries = new Hashtable<>();
//        for (int i=0;i<jsonObject.length();i++){
//
//            JSONObject jsonCountry = jsonObject.getJSONObject(i);
//            String nameCountry = jsonCountry.get("country").toString();
//
//            if (countries.contains(nameCountry)){
//
//                countries.get(nameCountry).setName(jsonCountry.get("country").toString());
//
//                long confirms= Long.getLong(jsonCountry.get("confirmed").toString());
//                long deaths=Long.getLong(jsonCountry.get("deaths").toString());
//                long recovereds=Long.getLong(jsonCountry.get("recovered").toString());
//                countries.get(nameCountry).addStats(confirms,deaths,recovereds);
//
//                if (jsonCountry.get("province").toString()!="null"){
//                    Province province = new Province();
//
//                    province.setName(jsonCountry.get("province").toString());
//
//                    province.setConfirmed(Long.getLong(jsonCountry.get("confirmed").toString()));
//                    province.setDeaths(Long.getLong(jsonCountry.get("deaths").toString()));
//                    province.setRecovered(Long.getLong(jsonCountry.get("recovered").toString()));
//
//                    countries.get(nameCountry).addProvince(province);
//                }
//
//            }else{
//                Country country = new Country();
//
//                country.setName(jsonCountry.get("country").toString());
//
//                long confirms= Long.getLong(jsonCountry.get("confirmed").toString());
//                long deaths=Long.getLong(jsonCountry.get("deaths").toString());
//                long recovereds=Long.getLong(jsonCountry.get("recovered").toString());
//
//                country.addStats(confirms,deaths,recovereds);
//
//                if (jsonCountry.get("province").toString()!="null"){
//                    Province province = new Province();
//
//                    province.setName(jsonCountry.get("province").toString());
//                    province.setConfirmed(Long.getLong(jsonCountry.get("confirmed").toString()));
//                    province.setDeaths(Long.getLong(jsonCountry.get("deaths").toString()));
//                    province.setRecovered(Long.getLong(jsonCountry.get("recovered").toString()));
//
//                    country.addProvince(province);
//                }
//
//                countries.put(country.getName(),country);
//            }
//        }
//
//        return countries;
//
//    }

//    public static void main(String[] args) {
//        connectionHttp connectionHttp = new connectionHttp();
//        try {
//
//            System.out.println(connectionHttp.getAllStatistics().keySet());
//        } catch (Exception e) {
//            System.out.println("F");
//        }
//    }
}
