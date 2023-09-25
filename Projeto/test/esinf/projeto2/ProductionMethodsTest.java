package esinf.projeto2;

import Projeto_2_ESINF.*;
import Projeto_2_ESINF.customExceptions.AreaNotFoundException;
import Projeto_2_ESINF.dataStructures.AVL;
import Projeto_2_ESINF.dataStructures.Three;
import Projeto_2_ESINF.dataStructures.TwoDTree;
import Projeto_2_ESINF.fileReaderUtils.ReadDataFromFiles;
import Projeto_2_ESINF.model.*;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductionMethodsTest {


    @Test
    public void nearestAreaToCertainDataTest() throws FileNotFoundException, AreaNotFoundException {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemname", "elementname");

        Area result = ProductionMethods.nearestAreaToCertainData(areaAVl, 40.7879676f, 12.000000f, "Blueberries", "Yield", 1999, "Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini.csv");

        Area italy = new AreaName(106, "'380", "Italy");

        Area expected = italy;

        assertEquals(true, expected.equals(result));
    }

    @Test
    public void nearestAreaToCertainDataWithAreaWithSameCodesButDifferentNameTest() throws FileNotFoundException, AreaNotFoundException {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemname", "elementname");

        Area result = ProductionMethods.nearestAreaToCertainData(areaAVl, 40.7879676f, 12.000000f, "Blueberries", "Yield", 1999, "Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini.csv");

        Area portugal = new AreaName(106, "'380", "Portugal");

        Area expected = portugal;

        assertEquals(false, expected.equals(result));
    }

    @Test
    public void nearestAreaToCertainDataWithAreaWithSameParametersAndNegativeCoordinatesTest() throws FileNotFoundException, AreaNotFoundException {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction_v2.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemname", "elementname");

        Area result = ProductionMethods.nearestAreaToCertainData(areaAVl, 38.7879676f, -8.000000f, "Apples", "Area harvested", 1990, "Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini_v2.csv");

        Area portugal = new AreaName(174, "'620", "Portugal");

        Area expected = portugal;

        assertEquals(true, expected.equals(result));
    }

    @Test
    public void nearestAreaToCertainDataNullParametersTest() throws FileNotFoundException, AreaNotFoundException {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemname", "elementname");

        assertThrows(NullPointerException.class, () -> {
            ProductionMethods.nearestAreaToCertainData(areaAVl, 40.7879676f, 12.000000f, null, null, 0, "Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini.csv");
        });
    }

    @Test
    public void nearestAreaToCertainDataNullTreeTest() throws FileNotFoundException, AreaNotFoundException {

        AVL<Area> areaAVl = new AVL<>();

        assertThrows(AreaNotFoundException.class, () -> {
            ProductionMethods.nearestAreaToCertainData(areaAVl, 40.7879676f, 12.000000f, "Blueberries", "Yield", 1999, "Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini.csv");
        });
    }

    @Test
    public void nearestAreaToCertainDataNoAreaCorrespondentTest() throws FileNotFoundException, AreaNotFoundException {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemname", "elementname");

        assertThrows(AreaNotFoundException.class, () -> {
            ProductionMethods.nearestAreaToCertainData(areaAVl, 40.7879676f, 12.000000f, "Blueberries", "Yield", 1000, "Production_Crops_Livestock_E_AreaCoordinates_shuffled_mini.csv");
        });
    }

    @Test
    public void nearestAreaToCertainDataWithLargeFileTest() throws AreaNotFoundException {
        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemname", "elementname");

        Area result = ProductionMethods.nearestAreaToCertainData(areaAVl, 35.9879676f, 137.9898879f ,"Cabbages", "Area harvested", 2009,  "Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv" );

        Area japan = new AreaName(110, "'392", "Japan");

        Area expected = japan;

        assertEquals(true, expected.equals(result));
    }

    @Test
    public void sumValuesInAreaOfProductionVerySmallFileTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long expectedSumValues = 129112000000L;
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 495, 5419, 1970, 0f, 100f, 0f, 100f);
        assertEquals(expectedSumValues, actualSumValues);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of mini file regular case: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionNoItemFoundVerySmallFileTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long expectedSumValues = 0;
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1, 5419, 1970, 0f, 100f, 0f, 100f);
        assertEquals(expectedSumValues, actualSumValues);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of mini file no item found: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionNoElementFoundVerySmallFileTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long expectedSumValues = 0;
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 495, 1, 1970, 0f, 100f, 0f, 100f);
        assertEquals(expectedSumValues, actualSumValues);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of mini file no element found: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionNoYearFoundVerySmallFileTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long expectedSumValues = 0;
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 495, 5419, 1, 0f, 100f, 0f, 100f);
        assertEquals(expectedSumValues, actualSumValues);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of mini file no year found: " + elapsedTime / 1000000 + " ms");
    }
    /*
    @Test
    public void sumValuesInAreaOfProductionWorldSmallTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of small file of World production: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionEUSmallTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of small file of EU production: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionCentralEUSmallTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_FR_GER_IT_PT_SP_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of small file of Central EU production: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionWorldMediumTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_medium.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 19f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of medium file of World production: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionEUMediumTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_medium.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of medium file of EU production: " + elapsedTime / 1000000 + " ms");
    }
/
    @Test
    public void sumValuesInAreaOfProductionCentralEUMediumTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_FR_GER_IT_PT_SP_shuffle_medium.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of medium file of Central EU production: " + elapsedTime / 1000000 + " ms");
    }*/

    @Test
    public void sumValuesInAreaOfProductionWorldLargeTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        long expectedValue = 684000000L;
        double startTime = System.nanoTime();

        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 995, 5510, 2020, 15f, 15.2f, 39.5f, 40f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertEquals(expectedValue, actualSumValues);
        System.out.println("Execution time of large file of World production: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionWorldLargeAllAreasTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        long expectedValue=1857922000000L;
        double startTime = System.nanoTime();

        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 995, 5510, 2020, -200f, 200f, -200f, 200f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertEquals(expectedValue,actualSumValues);
        System.out.println("Execution time of large file of World production all areas: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionWorldLargeNotFoundTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        double startTime = System.nanoTime();

        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1, 1, 1, 15f, 15.2f, 39.5f, 40f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;


        assertEquals(0, actualSumValues);
        System.out.println("Execution time of large file of World production: " + elapsedTime / 1000000 + " ms");

        assertEquals(0,actualSumValues);
        System.out.println("Execution time of large file of World production areas not found: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionWorldLargeNoItemTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        long expectedValue=0L;
        double startTime = System.nanoTime();

        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1, 5510, 2020, 15f, 15.2f, 39.5f, 40f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertEquals(expectedValue,actualSumValues);
        System.out.println("Execution time of large file of World production no item found: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionWorldLargeNoElementTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        long expectedValue=0L;
        double startTime = System.nanoTime();

        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 995, 1, 2020, 15f, 15.2f, 39.5f, 40f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertEquals(expectedValue,actualSumValues);
        System.out.println("Execution time of large file of World production no element found: " + elapsedTime / 1000000 + " ms");

    }

    @Test
    public void sumValuesInAreaOfProductionWorldLargeNoYearTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);

        long expectedValue=0L;
        double startTime = System.nanoTime();

        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 995, 5510, 0, 15f, 15.2f, 39.5f, 40f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;

        assertEquals(expectedValue,actualSumValues);
        System.out.println("Execution time of large file of World production no year found: " + elapsedTime / 1000000 + " ms");
    }
    /*
    @Test
    public void sumValuesInAreaOfProductionEULargeTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of large file of EU production: " + elapsedTime / 1000000 + " ms");
    }

    @Test
    public void sumValuesInAreaOfProductionCentralEULargeTest() throws FileNotFoundException {


        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_FR_GER_IT_PT_SP_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        List<Three<Area, Float, Float>> list = ReadDataFromFiles.storeDataFromFileCoordinates("Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv", areaAVl);

        TwoDTree<Area> two = new TwoDTree<Area>(list);


        double startTime = System.nanoTime();
        long actualSumValues = ProductionMethods.sumValuesInAreaOfProduction(areaAVl, two, 1182, 5510, 1992, 0f, 100f, 0f, 100f);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time of large file of Central EU production: " + elapsedTime / 1000000 + " ms");
    }*/

    @Test
    public void AvgValueByItemElementTest() throws Exception {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        Area areacode = new AreaCode(54, "208", "Denmark");
        Year year1 = new Year(2013);
        Year year2 = new Year(2014);

        //Item item = new ItemCode(515,"01341","Apples");
        // Element element = new ElementCode(5419,"Yield");


        double startTime = System.nanoTime();

        List<ItemElementAgregateValue> avg = ProductionMethods.AvgValueByItemElement(areaAVl, areacode, year1, year2);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time : " + elapsedTime / 1000000 + " ms");
        System.out.println(avg.get(0).avg());
        assertEquals(0.0, 225807000000.0 - avg.get(0).avg(), 0);

    }
    @Test
    public void AvgValueByItemElementTestNull() throws Exception {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        Area areacode = new AreaCode(5131314, "1231313131", "teste");
        Year year1 = new Year(2013);
        Year year2 = new Year(2014);


        double startTime = System.nanoTime();

        List<ItemElementAgregateValue> avg = ProductionMethods.AvgValueByItemElement(areaAVl,areacode,year1,year2);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time : " + elapsedTime / 1000000 + " ms");

        assertEquals(null,avg);

    }
    @Test
    public void AvgValueByItemElementTestSameYear() throws Exception {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        Area areacode = new AreaCode(54, "208", "Denmark");
        Year year1 = new Year(2013);
        Year year2 = new Year(2013);

        try {
            List<ItemElementAgregateValue> avg = ProductionMethods.AvgValueByItemElement(areaAVl, areacode, year1, year2);

        } catch (Exception e) {
            assertEquals(e.getMessage(), "The first year should be smaller than the second!");
        }
    }

    @Test
    public void AvgValueByItemElementTestYear0() throws Exception {

        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("VerySmallProduction.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        Area areacode = new AreaCode(54, "208", "Denmark");
        Year year1 = new Year(0);
        Year year2 = new Year(2013);

        try {
            List<ItemElementAgregateValue> avg = ProductionMethods.AvgValueByItemElement(areaAVl, areacode, year1, year2);

        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), "At least one of the given parameters does not exist in the data base or may be misspelled, please try again.\n\n");
        }
    }
    @Test
    public void AvgValueByItemElementTestLarge() throws Exception {
        double startTime = System.nanoTime();
        AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_World_shuffle_large.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

//        for (Area area : areaAVl.inOrder()) {
//            if (area.getAreaName().equals("India")) {
//                for (Item item : area.getItemList().inOrder()) {
//                    for (Element element : item.getElementList().inOrder()) {
//                        for (ProductionYear prod : element.getProductionYearList().inOrder()) {
//                            if (prod.getYear().getYear()==2013||prod.getYear().getYear()==2014){
//                            System.out.println(item.getItemName() + ", " + element.getElementName() + ", " + prod.getYear().getYear().intValue() + ", " + prod.getValue().getValue().longValue());
//                            } } } } } }

        Area areacode = new AreaCode(100, "356", "India");
        Year year1 = new Year(2013);
        Year year2 = new Year(2014);

        List<ItemElementAgregateValue> avg = ProductionMethods.AvgValueByItemElement(areaAVl,areacode,year1,year2);

        double stopTime = System.nanoTime();
        double elapsedTime = stopTime - startTime;
        System.out.println("Execution time : " + elapsedTime / 1000000 + " ms");
        assertEquals(0.0, 346671000000000.0  - avg.get(0).avg(), 0); //Item -> Sugar cane
        assertEquals(0.0, 346671000000000.0  - avg.get(1).avg(), 0); //Item -> Sugar Crops Primary
        assertEquals(0.0, 297700000000000.0  - avg.get(2).avg(), 0); //Item -> Cattle and Buffaloes
        assertEquals(0.0, 295459755000000.0  - avg.get(3).avg(), 0); //Item -> Cereals, Total
        assertEquals(0.0, 196900000000000.0  - avg.get(4).avg(), 0); //Item -> Sheep and Goats

    }

    @Test
    public void topNAreas0() throws Exception{
       // AVL<Area> areaAVl = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        Item inputItem1 = new ItemName(51, "'24310.01", "Beer of barley, malted");
        Element inputElement1 = new ElementName(5510, "Production");


        AVL<Area> areaAVL = ReadDataFromFiles.storeDataFromFileProduction("Production_Crops_Livestock_EU_shuffle_small.csv", "Production_Crops_Livestock_E_Flags.csv", "areaname", "itemcode", "elementcode");

        ArrayList<String> result = ProductionMethods.topNAreas(areaAVL, "Beer of barley, malted", "Production", 2);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Ireland");
        expected.add( "Spain");
       assertEquals(expected, result);

    }
}
