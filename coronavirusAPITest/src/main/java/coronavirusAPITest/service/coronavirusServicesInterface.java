package coronavirusAPITest.service;

import coronavirusAPITest.Model.Country;
import coronavirusAPITest.Model.CovidStat;

import java.util.List;

public interface coronavirusServicesInterface {
    List<Country> getAllStatistics() throws Exception;

    List<CovidStat> getStatisticsByCountryName(String country) throws Exception;;
}
