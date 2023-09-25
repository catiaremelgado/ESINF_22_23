package esinf.projeto1;

import Projeto_1_ESINF.Pair;
import Projeto_1_ESINF.exceptions.FruitNotFoundException;
import Projeto_1_ESINF.info.CompletedInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class testMaxProduction {


    @Test
    public void iftheFruitExists() throws FruitNotFoundException {

        CompletedInfo example = new CompletedInfo();


        example.addNewFruitAndCountry("Apples", "Portugal", 2014, 273721);
        example.addAnotherYear("Apples", "Portugal", 2016, 308363);
        example.addAnotherYear("Apples", "Portugal", 2015, 298363);
        example.addNewCountry("Apples", "Spain", 2001, 917409);
        example.addAnotherYear("Apples", "Spain", 2002, 998363);
        example.addAnotherYear("Apples", "Spain", 2021, 11740);
        example.addAnotherYear("Apples", "Spain", 2022, 21740);

        example.addNewFruitAndCountry("Apples1", "Portugal", 2010, 2713721);
        example.addAnotherYear("Apples1", "Portugal", 2009, 2583363);
        example.addNewCountry("Apples1", "Spain", 2006, 9174099);

        example.addNewFruitAndCountry("Apples2", "Portugal", 2014, 73721);
        example.addAnotherYear("Apples2", "Portugal", 2011, 25836);
        example.addNewCountry("Apples2", "Spain", 2021, 91740);


        List<Pair<String, Integer>> results = example.countrysNumberMaxQuantity("Apples");

        Assert.assertEquals(2, results.size());


        Pair<String, Integer> country = results.get(0);
        Pair<String, Integer> pair = new Pair<>("Portugal", 3);

        Assert.assertEquals(country.getLeft().equals(pair.getLeft()) && country.getRight() == pair.getRight(), true);

        country = results.get(1);
        pair = new Pair("Spain", 2);
        Assert.assertEquals(country.getLeft().equals(pair.getLeft()) && country.getRight() == pair.getRight(), true);
    }

    @Test
    public void ifTheFruitNull() {


        CompletedInfo example = new CompletedInfo();

        example.addNewFruitAndCountry("Apples", "Portugal", 2014, 73721);
        example.addAnotherYear("Apples", "Portugal", 2001, 58363);
        example.addNewCountry("Apples", "Spain", 2001, 91409);

        example.addNewFruitAndCountry("Apples1", "Portugal", 2010, 27721);
        example.addAnotherYear("Apples1", "Portugal", 2009, 25833);
        example.addNewCountry("Apples1", "Spain", 2006, 91740);


        try {
            example.countrysNumberMaxQuantity("Fruta");
        } catch (FruitNotFoundException e) {
            Assert.assertEquals("Fruit not found!", e.getMessage());
        }

    }
    @Test
    public void if2countrysHaveSameNumMaxQuantity() throws FruitNotFoundException {
        CompletedInfo completedInfo = new CompletedInfo();

        completedInfo.addNewFruitAndCountry("Banana", "Brazil", 2010, 721);
        completedInfo.addAnotherYear("Banana", "Brazil", 2011, 821);
        completedInfo.addAnotherYear("Banana", "Brazil", 2012, 921);
        completedInfo.addNewCountry("Banana", "Mexico", 2016, 421);
        completedInfo.addAnotherYear("Banana", "Mexico", 2017, 521);
        completedInfo.addAnotherYear("Banana", "Mexico", 2018, 621);


        List<Pair<String, Integer>> results = completedInfo.countrysNumberMaxQuantity("Banana");

        Assert.assertEquals(2, results.size());


        Pair<String, Integer> country = results.get(0);
        Pair<String, Integer> pair = new Pair<>("Brazil", 3);

        Assert.assertEquals(country.getLeft().equals(pair.getLeft()) && country.getRight() == pair.getRight(), true);

        country = results.get(1);
        pair = new Pair("Mexico", 3);
        Assert.assertEquals(country.getLeft().equals(pair.getLeft()) && country.getRight() == pair.getRight(), true);
    }
    }

