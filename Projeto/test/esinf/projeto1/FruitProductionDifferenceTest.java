package esinf.projeto1;

import Projeto_1_ESINF.FruitProductionDifference;
import Projeto_1_ESINF.info.CompletedInfo;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class FruitProductionDifferenceTest {



    @Test
    public void testBiggestProductionDifference() throws FileNotFoundException {


        CompletedInfo example = new CompletedInfo();

        example.addNewFruitAndCountry("Apples", "Portugal", 2014, 273721);

        example.addAnotherYear("Apples", "Portugal", 2001, 258363);

        example.addNewCountry("Apples", "Spain", 2001, 917409);

        FruitProductionDifference expectedDifference=new FruitProductionDifference(2001,2014,"Apples",15358);
        FruitProductionDifference difference=new FruitProductionDifference();
        difference=difference.biggestAbsoluteDifferenceInCountry(example,"Portugal");
        assertTrue(expectedDifference.equals(difference));

    }

    @Test
    public void testIfInfoNull(){

        CompletedInfo result = new CompletedInfo();

       FruitProductionDifference difference=new FruitProductionDifference();
        assertNull(difference.biggestAbsoluteDifferenceInCountry(result,"Portugal"));

    }

    @Test
    public void testSameProductionDifference() {


        CompletedInfo example = new CompletedInfo();

        example.addNewFruitAndCountry("Apples", "Portugal", 2014, 273721);

        example.addAnotherYear("Apples", "Portugal", 2001, 258363);
        example.addAnotherYear("Apples", "Portugal", 2015, 258363);

        example.addNewCountry("Apples", "Spain", 2001, 917409);

        FruitProductionDifference expectedDifference=new FruitProductionDifference(2014,2015,"Apples",15358);
        FruitProductionDifference difference=new FruitProductionDifference();
        difference=difference.biggestAbsoluteDifferenceInCountry(example,"Portugal");
        assertTrue(expectedDifference.equals(difference));

    }
}
