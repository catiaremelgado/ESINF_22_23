package esinf.projeto1;

import Projeto_1_ESINF.info.CompletedInfo;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class sortedMap {

    @Test
    public void testValueSort() throws FileNotFoundException {

        CompletedInfo input = new CompletedInfo();

        input.addNewFruitAndCountry("Apples", "Portugal", 2014, 273721);


        input.addAnotherYear("Apples", "Portugal", 2001, 258363);

        input.addNewCountry("Apples", "Spain", 2001, 258364);

        input.addNewCountry("Apples", "Mexico", 2001, 12345);

        List<String> expected=new ArrayList<>();
        expected.add("Spain");
        expected.add("Portugal");
        expected.add("Mexico");
        List<String> result=CompletedInfo.valueSort(input,"Apples",20);

        assertEquals(expected,result);
    }

    @Test
    public void testYearsWithSameQuantity() throws FileNotFoundException {

        CompletedInfo input = new CompletedInfo();

        input.addNewFruitAndCountry("Banana", "Portugal", 2014, 3889283);

        input.addAnotherYear("Banana", "Portugal", 2001, 10);

        input.addNewCountry("Banana", "Spain", 2001, 3889283);

        input.addNewCountry("Banana", "Mexico", 2001, 12345);
        List<String> expected=new ArrayList<>();
        expected.add("Spain");
        expected.add("Mexico");
        expected.add("Portugal");
        List<String> result=CompletedInfo.valueSort(input,"Banana",20);

        assertEquals(expected,result);
    }

    @Test
    public void testYearsFromTheSameCountryWithSameQuantity() throws FileNotFoundException {

        CompletedInfo input = new CompletedInfo();

        input.addNewFruitAndCountry("Orange", "Portugal", 2014, 3889283);

        input.addAnotherYear("Orange", "Portugal", 2001, 3889283);

        input.addNewCountry("Orange", "Spain", 2005, 675756);

        input.addNewCountry("Orange", "Mexico", 2001, 12345);
        List<String> expected=new ArrayList<>();
        expected.add("Portugal");
        expected.add("Mexico");
        expected.add("Spain");
        List<String> result=CompletedInfo.valueSort(input,"Orange",20);

        assertEquals(expected,result);
    }

    @Test
    public void testNegativeReceivingQuantity() throws FileNotFoundException {

        CompletedInfo input = new CompletedInfo();

        input.addNewFruitAndCountry("Cherry", "Portugal", 2014, 3889283);

        input.addAnotherYear("Cherry", "Portugal", 2001, 3889283);

        input.addNewCountry("Cherry", "Spain", 2005, 675756);

        input.addNewCountry("Cherry", "Mexico", 2001, 12345);

        List<String> result=CompletedInfo.valueSort(input,"Cherry",-1);

        assertNull(result);
    }

    @Test
    public void testIfTheCompleteInfoIsEmpty() throws FileNotFoundException {

        CompletedInfo input = new CompletedInfo();

        List<String> result=CompletedInfo.valueSort(input,"Avocado",20);

        assertNull(result);
    }

}
