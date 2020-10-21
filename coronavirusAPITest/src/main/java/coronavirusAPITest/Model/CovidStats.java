package coronavirusAPITest.Model;

import java.util.ArrayList;
import java.util.List;

public class CovidStats {
    private List<CovidStat> covid19Stats;

    public CovidStats() {
        this.covid19Stats = new ArrayList<CovidStat>();
    }

    public List<CovidStat> getCovid19Stats() {
        return covid19Stats;
    }

    public void setCovid19Stats(List<CovidStat> covid19Stats) {
        this.covid19Stats = covid19Stats;
    }

    @Override
    public String toString() {
        return "CovidStats{" +
                "covid19Stats=" + covid19Stats +
                '}';
    }
}