package Projeto_1_ESINF.entries;

import Projeto_1_ESINF.info.YearInfo;

/**
 * represents an entry in a list of countries
 */
public class CountryEntry {

    /**
     * country
     */
    private String country;

    /**
     * year information of country
     */
    private YearInfo year;

    /**
     * constructor for a country entry, with country and information about its years
     * @param country current entry's country
     * @param year year information about the country
     */
    public CountryEntry(String country, YearInfo year) {
        this.country = country;
        this.year = year;
    }

    /**
     * empty constructor of CountryEntry
     */
    public CountryEntry() {
    }

    /**
     * obtain the country name
     * @return string that represents the country's name
     */
    public String getCountry() {
        return country;
    }

    /**
     * information about each year of this country
     * @return yearInfo object that represents information of all the years in this country
     */
    public YearInfo getYearInfo() {
        return year;
    }
}
