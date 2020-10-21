package coronavirusAPITest.httpConection;

import coronavirusAPITest.Model.Country;
import coronavirusAPITest.Model.CovidStat;

import java.util.HashMap;
import java.util.List;

public interface connectionHttpInterface {
    HashMap<String, Country> getAllStatistics() throws Exception;

    List<CovidStat> getStatisticsByCountryName(String country) throws Exception;
}
