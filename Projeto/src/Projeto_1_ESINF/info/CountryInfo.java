package Projeto_1_ESINF.info;

import Projeto_1_ESINF.entries.CountryEntry;
import Projeto_1_ESINF.iterators.CountryInfoIterator;

import java.util.Objects;
import java.util.Iterator;
import java.util.TreeMap;

public class CountryInfo implements Iterable<CountryEntry>{ //implements Iterable<YearEntry> {

    private TreeMap<String, YearInfo> countryInfo;

    public CountryInfo() {
        this.countryInfo = new TreeMap<>();
    }


    public void add(String country, YearInfo year) {
        countryInfo.put(country, year);
    }

    public boolean containsCountry(String country) {
        return countryInfo.containsKey(country);
    }

    public YearInfo getByCountry(String country) {
        return countryInfo.get(country);
    }

    public TreeMap<String, YearInfo> getCountryInfo() {
        return countryInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof CountryInfo)) return false;
        CountryInfo countryInfo1 = (CountryInfo) o;
        return Objects.equals(countryInfo, countryInfo1.countryInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryInfo);
    }

    public YearInfo get(String country) {
        return countryInfo.get(country);
    }


    @Override
    public Iterator<CountryEntry> iterator() {
     return new CountryInfoIterator<>(this.countryInfo);
    }


}
