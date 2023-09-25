package esinf.projeto1;

import Projeto_1_ESINF.Store_Data;
import Projeto_1_ESINF.info.CompletedInfo;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;


public class testExtractedInfo {
    @Test
   public void testExtractedData() throws FileNotFoundException {

        String in = "Livro1.csv";

        CompletedInfo expected = new CompletedInfo();

        expected.addNewFruitAndCountry("Apples", "Portugal", 2014, 273721);

        expected.addAnotherYear("Apples", "Portugal", 2001, 258363);

        expected.addNewCountry("Apples", "Spain", 2001, 917409);

        Store_Data instance = new Store_Data();
        CompletedInfo result = instance.storeInfo(in);
        Assert.assertEquals(expected, result);
    }

     @Test
     public void testExtractedDataWithQuotes() throws FileNotFoundException {

         String in = "Livro4.csv";

          CompletedInfo expected = new CompletedInfo();

          expected.addNewFruitAndCountry("Apples", "Portugal", 2014, 2737212);

          expected.addAnotherYear("Apples", "Portugal", 2001, 1111111);

          Store_Data instance = new Store_Data();
          CompletedInfo result = instance.storeInfo(in);
          Assert.assertEquals(expected, result);
     }

    @Test
    public void testAddTwoFruits() throws FileNotFoundException {

        String in = "Livro3.csv";

        CompletedInfo expected = new CompletedInfo();

        expected.addNewFruitAndCountry("Bananas", "Portugal", 2014, 273721);

        expected.addNewFruitAndCountry("Apples", "Portugal", 2001, 258363);

        expected.addNewCountry("Apples", "Spain", 2001, 917409);

        Store_Data instance = new Store_Data();
        CompletedInfo result = instance.storeInfo(in);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAddNullData() throws FileNotFoundException {

        String in = "Livro5.csv";

        CompletedInfo expected = new CompletedInfo();

        expected.addNewFruitAndCountry("Apples", "Portugal", 2001, 258363);

        expected.addNewCountry("Apples", "Spain", 2001, 917409);

        Store_Data instance = new Store_Data();
        CompletedInfo result = instance.storeInfo(in);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testAddBrazilianNuts() throws FileNotFoundException {

        String in = "Livro6.csv";

        CompletedInfo expected = new CompletedInfo();

        expected.addNewFruitAndCountry("Brazil nuts, with shell", "Bolivia (Plurinational State of)", 1961, 2834);

        Store_Data instance = new Store_Data();
        CompletedInfo result = instance.storeInfo(in);
        Assert.assertEquals(expected, result);
    }
}
