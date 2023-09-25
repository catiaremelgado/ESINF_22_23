package Projeto_1_ESINF;

import Projeto_1_ESINF.entries.FruitEntry;
import Projeto_1_ESINF.entries.YearEntry;
import Projeto_1_ESINF.info.CompletedInfo;
import Projeto_1_ESINF.info.YearInfo;

import java.util.*;

/**
 * @author alexandraipatova
 * data structure to showcase data regarding fruit production difference of two years
 */
public class FruitProductionDifference {


    /**
     * earlier year of the fruit production difference
     */
    int yearBefore;

    /**
     * later year of the fruit production difference
     */

    int yearAfter;


    /**
     * fruit of the fruit production difference
     */
    String fruit;
    /**
     * value of the production difference between two years
     */
    int productionDifference;

    /**
     * constructor for the data structure regrading the difference in production over two years
     *
     * @param yearBefore           the lesser year to calculate the difference in production
     * @param yearAfter            the greater year to calculate the difference in production
     * @param fruit                the fruit to which the production difference refers
     * @param productionDifference production difference between the lesser year and the greater year
     */
    public FruitProductionDifference(int yearBefore, int yearAfter, String fruit, int productionDifference) {
        this.yearBefore = yearBefore;
        this.yearAfter = yearAfter;
        this.fruit = fruit;
        this.productionDifference = productionDifference;
    }

    /**
     * empty constructor for the production difference of two years
     */
    public FruitProductionDifference() {
    }

    /**
     * alters the lesser year of the production difference
     *
     * @param yearBefore the lesser year of the production difference
     */
    public void setYearBefore(int yearBefore) {
        this.yearBefore = yearBefore;
    }

    /**
     * alters the bigger year of the production difference
     *
     * @param yearAfter the bigger year of the production difference
     */
    public void setYearAfter(int yearAfter) {
        this.yearAfter = yearAfter;
    }

    /**
     * alter the fruit to which the production difference refers
     *
     * @param fruit fruit to which the production difference refers
     */
    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    /**
     * alters the production difference value between the two years
     *
     * @param productionDifference production difference value between the two years
     */
    public void setProductionDifference(int productionDifference) {
        this.productionDifference = productionDifference;
    }


    /**
     * calculates the biggest absolute difference in production of any fruit in a specific country
     *
     * @param productionList  the production list which contains all fruits and countries
     * @param searchedCountry the country which will be analysed in the funciton
     * @return null if the specified country wasn't found,
     * returns a FruitProductionDifference object with the collected data if not
     */

    public FruitProductionDifference biggestAbsoluteDifferenceInCountry(
            CompletedInfo productionList, String searchedCountry) {

        //the biggest difference found so far
        int biggestDifference = 0;

        //FruitProductionDifference object that collects necessary data
        FruitProductionDifference biggestValueDifferenceData = new FruitProductionDifference();

        //current production difference being analysed
        int difference;

        //iterates through the production list, through each fruit and its data
        for (FruitEntry fruit : productionList) {

            //get the country specified by the user
            YearInfo yearInfoOfCountry = fruit.getCountry().getByCountry(searchedCountry);

            //custom iterator to iterate through the previous year of production and next,
            // comparing the values in parallel
            Iterator<YearEntry> previousYearIt = yearInfoOfCountry.iterator();
            YearEntry prevYear;
            Iterator<YearEntry> yearIt = yearInfoOfCountry.iterator();
            YearEntry year = yearIt.next();//skip the first entry

            //if the country has info, we iterate through the iterator while it is not empty
            while (yearIt.hasNext()) {

                //obtain entries of our year to analyse
                prevYear = previousYearIt.next();
                year = yearIt.next();

                //calculate difference between the previous and current year's production
                difference = prevYear.getQuantity() - year.getQuantity();
                //get absolute value, so it cannot be negativa
                difference = Math.abs(difference);

                //check if the difference calculated is bigger or equal than the biggest difference
                // (equal because if a more recent year was found, we want the information which is more recent)
                if (difference >= biggestDifference) {

                    //set values
                    biggestValueDifferenceData.setYearBefore(prevYear.getYear());
                    biggestValueDifferenceData.setYearAfter(year.getYear());
                    biggestValueDifferenceData.setFruit(fruit.getFruit());
                    biggestValueDifferenceData.setProductionDifference(difference);
                    biggestDifference = difference;

                }

            }
        }
        //if there was no production difference, or simply there was no info, return null
        if (biggestDifference != 0) {
            return biggestValueDifferenceData;
        } else {
            return null;
        }

    }

    /**
     * showcase in string form the data
     *
     * @return a String that indicates all attributes of the object
     */
    @Override
    public String toString() {
        return "[" + this.yearBefore + "/" + this.yearAfter + "," + this.fruit + "," + this.productionDifference + "]";
    }

    /**
     * methd equals to compare two objects of same type
     *
     * @param obj object (assumed to be a FruitProductionDifference);
     * @return boolean that indicates if the object is equal or not to this
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FruitProductionDifference) {
            FruitProductionDifference o = (FruitProductionDifference) obj;
            return this.yearBefore == o.yearBefore && this.yearAfter == o.yearAfter && this.fruit.equals(o.fruit) && this.productionDifference == o.productionDifference;

        }
        return false;
    }
}
