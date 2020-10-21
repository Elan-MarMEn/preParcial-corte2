package coronavirusAPITest.Model;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private String countryname;
    private List<CovidStat> statList;
    private long confirmed;
    private long deaths;
    private long recovered;

    public Country(String countryname) {
        this.countryname = countryname;
        this.confirmed = 0;
        this.deaths = 0;
        this.recovered = 0;
        this.statList = new ArrayList<CovidStat>();
    }

    public void addStat(CovidStat stat) {
        confirmed += stat.getConfirmed();
        deaths += stat.getDeaths();
        recovered += stat.getRecovered();
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public List<CovidStat> getStatList() {
        return statList;
    }

    public void setStatList(List<CovidStat> statList) {
        this.statList = statList;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }
}
