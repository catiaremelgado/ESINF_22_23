package Projeto_1_ESINF.entries;

import Projeto_1_ESINF.info.CountryInfo;

/**
 * represents an entry in a list of fruits
 */
public class FruitEntry {

    /**
     * current fruit in the entry
     */
    private String fruit;

    /**
     * information about all the countries that produce this fruit
     */
    private CountryInfo country;

    /**
     * constructs a fruitEntry from the fruit name and information about the countries that produce it
     * @param fruit current fruit entry
     * @param country information about all the countries that produce this fruit
     */
    public FruitEntry(String fruit, CountryInfo country) {
        this.fruit = fruit;
        this.country = country;
    }

    /**
     * obtain value of the fruit name
     * @return string that represents fruit name
     */
    public String getFruit() {
        return fruit;
    }

    /**
     * alters the fruit name
     * @param fruit string that represents fruit name
     */
    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    /**
     * obtain all information about countries that produce this fruit
     * @return CountryInfo object that represents all information about countries that produce this fruit
     */
    public CountryInfo getCountry() {
        return country;
    }

    /**
     * alters information about the countries that produce the fruit
     * @param country information about the countries that produce the fruit
     */
    public void setCountry(CountryInfo country) {
        this.country = country;
    }

    /**
     * obtain all information about the countries that produce this fruit
     * @return CountryInfo object that contains all information about the countries that produce this fruit
     */
    public CountryInfo getCountryInfo() {
        return country;
    }
}
