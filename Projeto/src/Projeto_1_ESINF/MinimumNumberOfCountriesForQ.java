package Projeto_1_ESINF;

import Projeto_1_ESINF.entries.CountryEntry;
import Projeto_1_ESINF.entries.FruitEntry;
import Projeto_1_ESINF.entries.YearEntry;
import Projeto_1_ESINF.info.CompletedInfo;

import java.math.BigInteger;
import java.util.*;

public class MinimumNumberOfCountriesForQ {

    /**
     * Method that calculates how many countries (minimum number) together have a bigger production than the given one
     *
     * @param allInfo        it's the raw data from the csv file
     * @param wantedQuantity it's the quantity given to see how many countries (minimum number) together have a bigger
     *                       production than this parameter
     * @return the number of countries needed
     */
    public static Integer MinimumNumberOfCountriesForQ(CompletedInfo allInfo, BigInteger wantedQuantity) {

        /* if allInfo is empty or null, it returns null
        if quantity is less than zero it is returned null cause it is impossible to have negative quantities */
        if (allInfo.getCompletedInfo().isEmpty() || allInfo.getCompletedInfo() == null || wantedQuantity.compareTo(BigInteger.ZERO)==-1) {
            return null;
        /* if wanted quantity equals 0, then it returns that 1 countries are needed to fulfil this.
        This condition doesn't make sense because the list can be populated all with 0's in production therefore it would
        be needed 0 countries to fulfil this */
        /*} else if (wantedQuantity.compareTo(BigInteger.ZERO) == 0) {
            return 1;*/

        /* if quantity is different from 0 */
        } else {

            /* create a TreeMap and process all the information from Completed Info */
            TreeMap<String, BigInteger> listWithCountriesAndQuantities = ProcessCompletedInfoForExercise3(allInfo);
            /* creating a list to store just the values (because it is not needed the name of the countries)
            each line represents just one country
            it is used an ArrayList<> because Collections.reverse does not accept a Collection<> type to reverse
            and TreeMap.values() gives a Collection<> type */
            List<BigInteger> listWithQuantities = new ArrayList<>(listWithCountriesAndQuantities.values());
            /* sort the list of values because just the values may be not ordered
            and also reverse the order of elements in the list of values because Collections.sort() sorts the items
            in ascending order and in order to calculate the minimum number of countries needed we need this list to
            be in descending order */
            Collections.sort(listWithQuantities, Collections.reverseOrder());

            /* variable to control the minimum number of countries needed*/
            Integer numberOfCountries = 0;
            /* variable to control all the quantities seen and to access the positions of the list */
            BigInteger currentQuantity = BigInteger.ZERO;

            /* if the first value of the descending sorted list of values is 0, there's no need to sum its values
            to try and obtain a value bigger than the given quantity */
            if (listWithQuantities.get(0).compareTo(BigInteger.ZERO) != 0) {

                /* while the sum of production quantities from the list is smaller than the wanted quantity
                and there's still values in the list to analise */
                while (wantedQuantity.compareTo(currentQuantity) != -1 && numberOfCountries < listWithQuantities.size()) {
                    /* current quantity will be increased by summing to this variable the current value seen at the list */
                    currentQuantity = currentQuantity.add(listWithQuantities.get(numberOfCountries));
                    /* also, each time it's summed something into current quantity, the number of countries will be increased
                    to keep track of the number of countries and access next value of the list of values */
                    numberOfCountries++;
                }
            }

            /* if all the values from the list together are still smaller or equal to pretended quantity
            then it's returned 0 because there is no minimal number of countries which together have a
            prduction bigger than the pretended one */
            if (wantedQuantity.compareTo(currentQuantity) != -1) {
                return 0;
            }
            /* if all the values from the list together are bigger, then it's returned the number of countries
            calculated that fulfil the requirement */
            else {
                return numberOfCountries;
            }
        }
    }


    /**
     * Method to process all data from an CompletedInfo and transform it into a TreeMap<Country, Quantity>
     * needing quantity to be the sum of the productions of all fruits from all years of each country
     *
     * @param allInfo is the TreeMap<Fruit, TreeMap<Country, TreeMap<Year, Quantity> to be processed
     * @return a TreeMap<Country, quantity of all years for all fruits>
     */
    public static TreeMap<String, BigInteger> ProcessCompletedInfoForExercise3(CompletedInfo allInfo) {

        /* initialising TreeMap where the processed information will be stored
        the key is the country and the value is the quantity from all the years and all the fruits */
        TreeMap<String, BigInteger> myFinal = new TreeMap<>();

        /* if Completed Information has data stored, then it will be processed */
        if (!allInfo.getCompletedInfo().isEmpty()) {
            /* initialising the variable that will be used to store and calculate the quantities of production of each country
            the type BigInteger was chosen because (specially) depending on the unit this number can get huge */
            BigInteger quantity;

            /* iterate through each fruit */
            for (FruitEntry fruit : allInfo) {
                /* iterate through each country */
                for (CountryEntry country : fruit.getCountryInfo()) {
                    /* reinitialise quantity because in the next for each the next quantities are from another country */
                    quantity = BigInteger.ZERO;
                    /* get the country that is being iterated to after associate the quantity to the country/ key in the TreeMap */
                    String currentCountry = country.getCountry();
                    /* iterate through each year to get quantities */
                    for (YearEntry year : country.getYearInfo()) {
                        /* sum all quantities from all years of the current country and the (current) fruit */
                        quantity = quantity.add(new BigInteger(String.valueOf(year.getQuantity())));
                    }
                    /* if the key does not exist yet, it will be "initialized"
                    or, if the country it's not yet on the TreeMap, create an entry for it*/
                    if (!myFinal.containsKey(currentCountry)) {
                        myFinal.put(currentCountry, BigInteger.ZERO);
                    }
                    /* add the quantity of that years from current country for that fruit to the value of its correspondent key */
                    myFinal.put(currentCountry, myFinal.get(currentCountry).add(quantity));
                }
            }
        }
        /* returns final TreeMap with all the information treated (if there was information to treat) */
        return myFinal;
    }
}
