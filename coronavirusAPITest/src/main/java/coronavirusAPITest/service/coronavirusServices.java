package coronavirusAPITest.service;


import coronavirusAPITest.Model.Country;
import coronavirusAPITest.Model.CovidStat;
import coronavirusAPITest.httpConection.connectionHttpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class coronavirusServices implements coronavirusServicesInterface {

    @Autowired
    @Qualifier("connect")
    connectionHttpInterface connectionHttpInterface;

    @Override
    public List<Country> getAllStatistics() throws Exception {
        HashMap<String,Country> dict=connectionHttpInterface.getAllStatistics();
        List<Country> countries=new ArrayList<Country>();
        for (Country country: dict.values()) {
            countries.add(country);

        }
        return countries;
    }

    @Override
    public List<CovidStat> getStatisticsByCountryName(String country) throws Exception {
    /*System.out.println("getStatisticsByCountryName(String country)");
    System.out.println(country);*/
        List<CovidStat> listCovidStat=connectionHttpInterface.getStatisticsByCountryName(country);
        return listCovidStat;
    }

//    public static void main(String[] args) throws Exception {
//        coronavirusServices coronavirusServices= new coronavirusServices();
//        coronavirusServices.getAllStatistics();
//    }
}


