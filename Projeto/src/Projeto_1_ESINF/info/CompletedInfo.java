package Projeto_1_ESINF.info;

import Projeto_1_ESINF.CountryYearQuantity;
import Projeto_1_ESINF.Pair;
import Projeto_1_ESINF.entries.CountryEntry;
import Projeto_1_ESINF.entries.FruitEntry;
import Projeto_1_ESINF.entries.YearEntry;
import Projeto_1_ESINF.exceptions.FruitNotFoundException;
import Projeto_1_ESINF.iterators.FruitInfoIterator;

import java.util.*;


public class CompletedInfo implements Iterable<FruitEntry> {
    private TreeMap<String, CountryInfo> completedInfo;

    public CompletedInfo() {
        this.completedInfo = new TreeMap<>();
    }


    public void add(String fruit, CountryInfo country) {
        completedInfo.put(fruit, country);
    }

    public boolean containsFruit(String fruit) {
        return completedInfo.containsKey(fruit);
    }

    public boolean containsCountry(String fruit, String country) {
        return completedInfo.get(fruit).containsCountry(country);
    }

    public void addAnotherYear(String fruit, String country, int year, int quantity) {
        completedInfo.get(fruit).getByCountry(country).add(year, quantity);
    }

    public CountryInfo getCountryInfo(String fruit) {
        return completedInfo.get(fruit);
    }


    public void addNewCountry(String fruit, String country, int year, int quantity) {
        completedInfo.get(fruit).add(country, newYearInfo(year, quantity));
    }

    public void addNewFruitAndCountry(String fruit, String country, int year, int quantity) {
        completedInfo.put(fruit, newCountry(country, (newYearInfo(year, quantity))));
    }

    public YearInfo newYearInfo(int year, int quantity) {
        YearInfo years = new YearInfo();
        years.add(year, quantity);

        return years;
    }

    public CountryInfo newCountry(String country, YearInfo year) {
        CountryInfo countrys = new CountryInfo();
        countrys.add(country, year);

        return countrys;
    }


    public TreeMap<String, CountryInfo> getCompletedInfo() {
        return completedInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof CompletedInfo)) return false;
        CompletedInfo that = (CompletedInfo) o;
        return Objects.equals(completedInfo, that.completedInfo);
    }

    @Override
    public Iterator<FruitEntry> iterator() {
        return new FruitInfoIterator<>(this.completedInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completedInfo);
    }

    /**
     * Sorts completedInfo of a certain fruit, which its production is higher than a determined quantity
     * @param completedInfo completedInfo object to filter
     * @param fruit the information of this chosen fruit will be sorted
     * @param quantity the quantity produced of this fruit will be sorted higher or equal to this parameter
     * @return a list of Strings that represents the information sorted by the order
     */
    public static List<String> valueSort(CompletedInfo completedInfo, String fruit, Integer quantity) {

        //information about the countries of the chosen fruit
        CountryInfo countryInfo = completedInfo.getCountryInfo(fruit);

        //list in which the sorted countries will be placed
        List<String> result = new ArrayList<>();

        //auxiliary structure to store the data for sorting
        TreeMap<Integer, List<Pair<Integer, String>>> data = new TreeMap<>();

        if(countryInfo!=null && quantity >= 0){
        //if the fruit has countries which produce it, and if the analyzed quantity is higher or equal to zero we can iterate

            //iterates through each country's information
            for (CountryEntry country : countryInfo) {
                //iterates through each year's information, relating to country
                for (YearEntry year : country.getYearInfo()) {

                    //if the quantity produced in the current year is higher than the target quantity, we store its data
                    if (year.getQuantity() >= quantity) {

                        //if the year is not already included in the list
                        List<Pair<Integer, String>> list = data.get(year.getYear());
                        if (list == null) { //create new list
                            list = new ArrayList<>();
                        }
                        //add the current quantity and the country to the list
                        list.add(new Pair<Integer, String>(year.getQuantity(), country.getCountry()));

                        //add the list of quantities and corresponding country to the respective year
                        data.put(year.getYear(), list);
                    }
                }
            }

            //analyse each year individually
            for (Map.Entry<Integer, List<Pair<Integer, String>>> entry : data.entrySet()) {

                //sort the information regarding quantity in descending order
                //Collections.sort(entry.getValue());
                Collections.reverse(entry.getValue());

                //add the countries by order of quantity in descending order, if they are not already on the list
                for (Pair<Integer, String> pair : entry.getValue()) {

                    if (!result.contains(pair.getRight())) {
                        result.add(pair.getRight());
                    }
                }
            }
        }
        //if nothing was found
        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }

    }

    public List<Pair<String, Integer>> countrysNumberMaxQuantity(String fruitName) throws FruitNotFoundException {

        List<CountryYearQuantity> list = new ArrayList<>();
        CountryInfo countryInfo = this.completedInfo.get(fruitName);
            if (countryInfo==null){
                throw new FruitNotFoundException("Fruit not found!");
            }


        for (CountryEntry countryEntry : countryInfo) {

            String country = countryEntry.getCountry();

            for (YearEntry yearEntry : countryInfo.getByCountry(country)) {
                int year = yearEntry.getYear();
                int quantity = yearEntry.getQuantity();
                list.add(new CountryYearQuantity(country, year, quantity));
            }
        }

        list.sort(new Comparator<CountryYearQuantity>() {
                      @Override
                      public int compare(CountryYearQuantity o1, CountryYearQuantity o2) {

                          int cmp = o1.getCountry().compareTo(o2.getCountry());

                          if (cmp == 0) {
                              return o1.getYear() - o2.getYear();
                          } else {
                              return cmp;
                          }
                      }
                  }
        );

        // Country, Year, Quantity => Country, Years Growing

        // <Country, List<Years Growing>
        HashMap<String, Integer> preprocess = new HashMap<>();
        int count = 0, max = 0;
        Integer value;
        CountryYearQuantity info1 = null, info2;

        for (int i = 0; i < list.size() - 1; i++) {

            info1 = list.get(i);
            info2 = list.get(i+1);

            if (info1.getCountry().equals(info2.getCountry()) && info1.getYear() + 1 == info2.getYear()) {
                if (info1.getQuantity() < info2.getQuantity()) {
                    count++;
                } else {
                    if (max < count) {
                        max = count;
                    }
                    count = 0;
                }
            } else {
                if (max < count) {
                    max = count;
                }
                count = 0;
                if ((value = preprocess.get(info1.getCountry())) != null) {
                    if (value < max+1) {
                        preprocess.put(info1.getCountry(), max+1);
                    }
                } else {
                    preprocess.put(info1.getCountry(), max+1);
                }
                max = 0;
            }
        }

        // last compare
        if (max < count) {
            max = count;
        }
        count = 0;
        if (info1 != null) {
            if ((value = preprocess.get(info1.getCountry())) != null) {
                if (value < max + 1) {
                    preprocess.put(info1.getCountry(), max + 1);
                }
            } else {
                preprocess.put(info1.getCountry(), max + 1);
            }
        }

        List<Pair<String, Integer>> result = new ArrayList<>();

        Set<String> keys = preprocess.keySet();

        for (String key: keys) {
            result.add(new Pair(key, preprocess.get(key)));
        }

        result.sort(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o2.getRight() - o1.getRight();
            }
        });

        return result;

    }
}
